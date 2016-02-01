package com.smartair.utils;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.text.CharacterIterator;
//import java.util.*;
//
//import org.apache.commons.io.FileUtils;
//import org.deeplearning4j.datasets.iterator.DataSetIterator;
//import org.deeplearning4j.nn.api.Layer;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.Updater;
//import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
//import org.deeplearning4j.nn.conf.layers.GravesLSTM;
//import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
//import org.nd4j.linalg.api.ndarray.INDArray;
//import org.nd4j.linalg.dataset.DataSet;
//import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
//import org.nd4j.linalg.factory.Nd4j;
//import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction;
//
///**GravesLSTM Character modelling example
// * @author Alex Black
//
//Example: Train a LSTM RNN to generates text, one character at a time.
//This example is somewhat inspired by Andrej Karpathy's blog post,
//"The Unreasonable Effectiveness of Recurrent Neural Networks"
//http://karpathy.github.io/2015/05/21/rnn-effectiveness/
//
//Note that this example has not been well tuned - better performance is likely possible with better hyperparameters
//
//Some differences between this example and Karpathy's work:
//- The LSTM architectures appear to differ somewhat. GravesLSTM has peephole connections that
//Karpathy's char-rnn implementation appears to lack. See GravesLSTM javadoc for details.
//There are pros and cons to both architectures (addition of peephole connections is a more powerful
//model but has more parameters per unit), though they are not radically different in practice.
//- Karpathy uses truncated backpropagation through time (BPTT) on full character
//sequences, whereas this example uses standard (non-truncated) BPTT on partial/subset sequences.
//Truncated BPTT is probably the preferred method of training for this sort of problem, and is configurable
//using the .backpropType(BackpropType.TruncatedBPTT).tBPTTForwardLength().tBPTTBackwardLength() options
//
//This example is set up to train on the Complete Works of William Shakespeare, downloaded
//from Project Gutenberg. Training on other text sources should be relatively easy to implement.
// */
//public class GravesLSTMCharModellingExample {
//    public static void main( String[] args ) throws Exception {
//        int lstmLayerSize = 200;					//Number of units in each GravesLSTM layer
//        int miniBatchSize = 32;						//Size of mini batch to use when  training
//        int examplesPerEpoch = 50 * miniBatchSize;	//i.e., how many examples to learn on between generating samples
//        int exampleLength = 100;					//Length of each training example
//        int numEpochs = 30;							//Total number of training + sample generation epochs
//        int nSamplesToGenerate = 4;					//Number of samples to generate after each training epoch
//        int nCharactersToSample = 300;				//Length of each sample to generate
//        String generationInitialization = null;		//Optional character initialization; a random character is used if null
//        // Above is Used to 'prime' the LSTM with a character sequence to continue/complete.
//        // Initialization characters must all be in CharacterIterator.getMinimalCharacterSet() by default
//        Random rng = new Random(12345);
//
//        //Get a DataSetIterator that handles vectorization of text into something we can use to train
//        // our GravesLSTM network.
//        CharacterIterator iter = getShakespeareIterator(miniBatchSize,exampleLength,examplesPerEpoch);
//        int nOut = iter.totalOutcomes();
//
//        //Set up network configuration:
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).iterations(1)
//                .learningRate(0.1)
//                .rmsDecay(0.95)
//                .seed(12345)
//                .regularization(true)
//                .l2(0.001)
//                .list(3)
//                .layer(0, new GravesLSTM.Builder().nIn(iter.inputColumns()).nOut(lstmLayerSize)
//                        .updater(Updater.RMSPROP)
//                        .activation("tanh").weightInit(WeightInit.DISTRIBUTION)
//                        .dist(new UniformDistribution(-0.08, 0.08)).build())
//                .layer(1, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
//                        .updater(Updater.RMSPROP)
//                        .activation("tanh").weightInit(WeightInit.DISTRIBUTION)
//                        .dist(new UniformDistribution(-0.08, 0.08)).build())
//                .layer(2, new RnnOutputLayer.Builder(LossFunction.MCXENT).activation("softmax")        //MCXENT + softmax for classification
//                        .updater(Updater.RMSPROP)
//                        .nIn(lstmLayerSize).nOut(nOut).weightInit(WeightInit.DISTRIBUTION)
//                        .dist(new UniformDistribution(-0.08, 0.08)).build())
//                .pretrain(false).backprop(true)
//                .build();
//
//        MultiLayerNetwork net = new MultiLayerNetwork(conf);
//        net.init();
//        net.setListeners(new ScoreIterationListener(1));
//
//        //Print the  number of parameters in the network (and for each layer)
//        Layer[] layers = net.getLayers();
//        int totalNumParams = 0;
//        for( int i=0; i<layers.length; i++ ){
//            int nParams = layers[i].numParams();
//            System.out.println("Number of parameters in layer " + i + ": " + nParams);
//            totalNumParams += nParams;
//        }
//        System.out.println("Total number of network parameters: " + totalNumParams);
//
//        //Do training, and then generate and print samples from network
//        for( int i=0; i<numEpochs; i++ ){
//            net.fit(iter);
//
//            System.out.println("--------------------");
//            System.out.println("Completed epoch " + i );
//            System.out.println("Sampling characters from network given initialization \""+ (generationInitialization == null ? "" : generationInitialization) +"\"");
//            String[] samples = sampleCharactersFromNetwork(generationInitialization,net,iter,rng,nCharactersToSample,nSamplesToGenerate);
//            for( int j=0; j<samples.length; j++ ){
//                System.out.println("----- Sample " + j + " -----");
//                System.out.println(samples[j]);
//                System.out.println();
//            }
//
//            iter.reset();	//Reset iterator for another epoch
//        }
//
//        System.out.println("\n\nExample complete");
//    }
//
//    /** Downloads Shakespeare training data and stores it locally (temp directory). Then set up and return a simple
//     * DataSetIterator that does vectorization based on the text.
//     * @param miniBatchSize Number of text segments in each training mini-batch
//     * @param exampleLength Number of characters in each text segment.
//     * @param examplesPerEpoch Number of examples we want in an 'epoch'.
//     */
//    private static CharacterIterator getShakespeareIterator(int miniBatchSize, int exampleLength, int examplesPerEpoch) throws Exception{
//        //The Complete Works of William Shakespeare
//        //5.3MB file in UTF-8 Encoding, ~5.4 million characters
//        //https://www.gutenberg.org/ebooks/100
//        String url = "https://s3.amazonaws.com/dl4j-distribution/pg100.txt";
//        String tempDir = System.getProperty("java.io.tmpdir");
//        String fileLocation = tempDir + "/Shakespeare.txt";	//Storage location from downloaded file
//        File f = new File(fileLocation);
//        if( !f.exists() ){
//            FileUtils.copyURLToFile(new URL(url), f);
//            System.out.println("File downloaded to " + f.getAbsolutePath());
//        } else {
//            System.out.println("Using existing text file at " + f.getAbsolutePath());
//        }
//
//        if(!f.exists()) throw new IOException("File does not exist: " + fileLocation);	//Download problem?
//
//        char[] validCharacters = CharacterIterator.getMinimalCharacterSet();	//Which characters are allowed? Others will be removed
//        return new CharacterIterator(fileLocation, Charset.forName("UTF-8"),
//                miniBatchSize, exampleLength, examplesPerEpoch, validCharacters, new Random(12345),true);
//    }
//
//    /** Generate a sample from the network, given an (optional, possibly null) initialization. Initialization
//     * can be used to 'prime' the RNN with a sequence you want to extend/continue.<br>
//     * Note that the initalization is used for all samples
//     * @param initialization String, may be null. If null, select a random character as initialization for all samples
//     * @param charactersToSample Number of characters to sample from network (excluding initialization)
//     * @param net MultiLayerNetwork with one or more GravesLSTM/RNN layers and a softmax output layer
//     * @param iter CharacterIterator. Used for going from indexes back to characters
//     */
//    private static String[] sampleCharactersFromNetwork( String initialization, MultiLayerNetwork net,
//                                                         CharacterIterator iter, Random rng, int charactersToSample, int numSamples ){
//        //Set up initialization. If no initialization: use a random character
//        if( initialization == null ){
//            initialization = String.valueOf(iter.getRandomCharacter());
//        }
//
//        //Create input for initialization
//        INDArray initializationInput = Nd4j.zeros(numSamples, iter.inputColumns(), initialization.length());
//        char[] init = initialization.toCharArray();
//        for( int i=0; i<init.length; i++ ){
//            int idx = iter.convertCharacterToIndex(init[i]);
//            for( int j=0; j<numSamples; j++ ){
//                initializationInput.putScalar(new int[]{j,idx,i}, 1.0f);
//            }
//        }
//
//        StringBuilder[] sb = new StringBuilder[numSamples];
//        for( int i=0; i<numSamples; i++ ) sb[i] = new StringBuilder(initialization);
//
//        //Sample from network (and feed samples back into input) one character at a time (for all samples)
//        //Sampling is done in parallel here
//        net.rnnClearPreviousState();
//        INDArray output = net.rnnTimeStep(initializationInput);
//        output = output.tensorAlongDimension(output.size(2)-1,1,0);	//Gets the last time step output
//
//        for( int i=0; i<charactersToSample; i++ ){
//            //Set up next input (single time step) by sampling from previous output
//            INDArray nextInput = Nd4j.zeros(numSamples,iter.inputColumns());
//            //Output is a probability distribution. Sample from this for each example we want to generate, and add it to the new input
//            for( int s=0; s<numSamples; s++ ){
//                double[] outputProbDistribution = new double[iter.totalOutcomes()];
//                for( int j=0; j<outputProbDistribution.length; j++ ) outputProbDistribution[j] = output.getDouble(s,j);
//                int sampledCharacterIdx = sampleFromDistribution(outputProbDistribution,rng);
//
//                nextInput.putScalar(new int[]{s,sampledCharacterIdx}, 1.0f);		//Prepare next time step input
//                sb[s].append(iter.convertIndexToCharacter(sampledCharacterIdx));	//Add sampled character to StringBuilder (human readable output)
//            }
//
//            output = net.rnnTimeStep(nextInput);	//Do one time step of forward pass
//        }
//
//        String[] out = new String[numSamples];
//        for( int i=0; i<numSamples; i++ ) out[i] = sb[i].toString();
//        return out;
//    }
//
//    /** Given a probability distribution over discrete classes, sample from the distribution
//     * and return the generated class index.
//     * @param distribution Probability distribution over classes. Must sum to 1.0
//     */
//    private static int sampleFromDistribution( double[] distribution, Random rng ){
//        double d = rng.nextDouble();
//        double sum = 0.0;
//        for( int i=0; i<distribution.length; i++ ){
//            sum += distribution[i];
//            if( d <= sum ) return i;
//        }
//        //Should never happen if distribution is a valid probability distribution
//        throw new IllegalArgumentException("Distribution is invalid? d="+d+", sum="+sum);
//    }
//
//    private static class CharacterIterator implements DataSetIterator {
//        private static final long serialVersionUID = -7287833919126626356L;
//        private static final int MAX_SCAN_LENGTH = 200;
//        private char[] validCharacters;
//        private Map<Character,Integer> charToIdxMap;
//        private char[] fileCharacters;
//        private int exampleLength;
//        private int miniBatchSize;
//        private int numExamplesToFetch;
//        private int examplesSoFar = 0;
//        private Random rng;
//        private final int numCharacters;
//        private final boolean alwaysStartAtNewLine;
//
//        public CharacterIterator(String path, int miniBatchSize, int exampleSize, int numExamplesToFetch ) throws IOException {
//            this(path,Charset.defaultCharset(),miniBatchSize,exampleSize,numExamplesToFetch,getDefaultCharacterSet(), new Random(),true);
//        }
//
//        /**
//         * @param textFilePath Path to text file to use for generating samples
//         * @param textFileEncoding Encoding of the text file. Can try Charset.defaultCharset()
//         * @param miniBatchSize Number of examples per mini-batch
//         * @param exampleLength Number of characters in each input/output vector
//         * @param numExamplesToFetch Total number of examples to fetch (must be multiple of miniBatchSize). Used in hasNext() etc methods
//         * @param validCharacters Character array of valid characters. Characters not present in this array will be removed
//         * @param rng Random number generator, for repeatability if required
//         * @param alwaysStartAtNewLine if true, scan backwards until we find a new line character (up to MAX_SCAN_LENGTH in case
//         *  of no new line characters, to avoid scanning entire file)
//         * @throws IOException If text file cannot  be loaded
//         */
//        public CharacterIterator(String textFilePath, Charset textFileEncoding, int miniBatchSize, int exampleLength,
//                                 int numExamplesToFetch, char[] validCharacters, Random rng, boolean alwaysStartAtNewLine ) throws IOException {
//            if( !new File(textFilePath).exists()) throw new IOException("Could not access file (does not exist): " + textFilePath);
//            if(numExamplesToFetch % miniBatchSize != 0 ) throw new IllegalArgumentException("numExamplesToFetch must be a multiple of miniBatchSize");
//            if( miniBatchSize <= 0 ) throw new IllegalArgumentException("Invalid miniBatchSize (must be >0)");
//            this.validCharacters = validCharacters;
//            this.exampleLength = exampleLength;
//            this.miniBatchSize = miniBatchSize;
//            this.numExamplesToFetch = numExamplesToFetch;
//            this.rng = rng;
//            this.alwaysStartAtNewLine = alwaysStartAtNewLine;
//
//            //Store valid characters is a map for later use in vectorization
//            charToIdxMap = new HashMap<>();
//            for( int i=0; i<validCharacters.length; i++ ) charToIdxMap.put(validCharacters[i], i);
//            numCharacters = validCharacters.length;
//
//            //Load file and convert contents to a char[]
//            boolean newLineValid = charToIdxMap.containsKey('\n');
//            List<String> lines = Files.readAllLines(new File(textFilePath).toPath(), textFileEncoding);
//            int maxSize = lines.size();	//add lines.size() to account for newline characters at end of each line
//            for( String s : lines ) maxSize += s.length();
//            char[] characters = new char[maxSize];
//            int currIdx = 0;
//            for( String s : lines ){
//                char[] thisLine = s.toCharArray();
//                for( int i=0; i<thisLine.length; i++ ){
//                    if( !charToIdxMap.containsKey(thisLine[i]) ) continue;
//                    characters[currIdx++] = thisLine[i];
//                }
//                if(newLineValid) characters[currIdx++] = '\n';
//            }
//
//            if( currIdx == characters.length ){
//                fileCharacters = characters;
//            } else {
//                fileCharacters = Arrays.copyOfRange(characters, 0, currIdx);
//            }
//            if( exampleLength >= fileCharacters.length ) throw new IllegalArgumentException("exampleLength="+exampleLength
//                    +" cannot exceed number of valid characters in file ("+fileCharacters.length+")");
//
//            int nRemoved = maxSize - fileCharacters.length;
//            System.out.println("Loaded and converted file: " + fileCharacters.length + " valid characters of "
//                    + maxSize + " total characters (" + nRemoved + " removed)");
//        }
//
//        /** A minimal character set, with a-z, A-Z, 0-9 and common punctuation etc */
//        public static char[] getMinimalCharacterSet(){
//            List<Character> validChars = new LinkedList<>();
//            for(char c='a'; c<='z'; c++) validChars.add(c);
//            for(char c='A'; c<='Z'; c++) validChars.add(c);
//            for(char c='0'; c<='9'; c++) validChars.add(c);
//            char[] temp = {'!', '&', '(', ')', '?', '-', '\'', '"', ',', '.', ':', ';', ' ', '\n', '\t'};
//            for( char c : temp ) validChars.add(c);
//            char[] out = new char[validChars.size()];
//            int i=0;
//            for( Character c : validChars ) out[i++] = c;
//            return out;
//        }
//
//        /** As per getMinimalCharacterSet(), but with a few extra characters */
//        public static char[] getDefaultCharacterSet(){
//            List<Character> validChars = new LinkedList<>();
//            for(char c : getMinimalCharacterSet() ) validChars.add(c);
//            char[] additionalChars = {'@', '#', '$', '%', '^', '*', '{', '}', '[', ']', '/', '+', '_',
//                    '\\', '|', '<', '>'};
//            for( char c : additionalChars ) validChars.add(c);
//            char[] out = new char[validChars.size()];
//            int i=0;
//            for( Character c : validChars ) out[i++] = c;
//            return out;
//        }
//
//        public char convertIndexToCharacter( int idx ){
//            return validCharacters[idx];
//        }
//
//        public int convertCharacterToIndex( char c ){
//            return charToIdxMap.get(c);
//        }
//
//        public char getRandomCharacter(){
//            return validCharacters[(int) (rng.nextDouble()*validCharacters.length)];
//        }
//
//        public boolean hasNext() {
//            return examplesSoFar + miniBatchSize <= numExamplesToFetch;
//        }
//
//        public DataSet next() {
//            return next(miniBatchSize);
//        }
//
//        public DataSet next(int num) {
//            if( examplesSoFar+num > numExamplesToFetch ) throw new NoSuchElementException();
//            //Allocate space:
//            INDArray input = Nd4j.zeros(new int[]{num,numCharacters,exampleLength});
//            INDArray labels = Nd4j.zeros(new int[]{num,numCharacters,exampleLength});
//
//            int maxStartIdx = fileCharacters.length - exampleLength;
//
//            //Randomly select a subset of the file. No attempt is made to avoid overlapping subsets
//            // of the file in the same minibatch
//            for( int i=0; i<num; i++ ){
//                int startIdx = (int) (rng.nextDouble()*maxStartIdx);
//                int endIdx = startIdx + exampleLength;
//                int scanLength = 0;
//                if(alwaysStartAtNewLine){
//                    while(startIdx >= 1 && fileCharacters[startIdx-1] != '\n' && scanLength++ < MAX_SCAN_LENGTH ){
//                        startIdx--;
//                        endIdx--;
//                    }
//                }
//
//                int currCharIdx = charToIdxMap.get(fileCharacters[startIdx]);	//Current input
//                int c=0;
//                for( int j=startIdx+1; j<=endIdx; j++, c++ ){
//                    int nextCharIdx = charToIdxMap.get(fileCharacters[j]);		//Next character to predict
//                    input.putScalar(new int[]{i,currCharIdx,c}, 1.0);
//                    labels.putScalar(new int[]{i,nextCharIdx,c}, 1.0);
//                    currCharIdx = nextCharIdx;
//                }
//            }
//
//            examplesSoFar += num;
//            return new DataSet(input,labels);
//        }
//
//        public int totalExamples() {
//            return numExamplesToFetch;
//        }
//
//        public int inputColumns() {
//            return numCharacters;
//        }
//
//        public int totalOutcomes() {
//            return numCharacters;
//        }
//
//        public void reset() {
//            examplesSoFar = 0;
//        }
//
//        public int batch() {
//            return miniBatchSize;
//        }
//
//        public int cursor() {
//            return examplesSoFar;
//        }
//
//        public int numExamples() {
//            return numExamplesToFetch;
//        }
//
//        public void setPreProcessor(DataSetPreProcessor preProcessor) {
//            throw new UnsupportedOperationException("Not implemented");
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//
//    }
//}