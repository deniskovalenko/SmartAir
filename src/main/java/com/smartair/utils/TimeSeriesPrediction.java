package com.smartair.utils;


//import com.smartair.model.entity.StatisticModel;
//import org.canova.api.records.reader.RecordReader;
//import org.canova.api.records.reader.impl.CSVRecordReader;
//import org.canova.api.records.reader.impl.CollectionRecordReader;
//import org.canova.api.split.FileSplit;
//import org.canova.api.writable.Writable;
//import org.deeplearning4j.datasets.canova.RecordReaderDataSetIterator;
//import org.deeplearning4j.datasets.iterator.DataSetIterator;
//import org.deeplearning4j.nn.api.OptimizationAlgorithm;
//import org.deeplearning4j.nn.conf.GradientNormalization;
//import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
//import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
//import org.deeplearning4j.nn.conf.Updater;
//import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
//import org.deeplearning4j.nn.conf.layers.GravesLSTM;
//import org.deeplearning4j.nn.conf.layers.OutputLayer;
//import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
//import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
//import org.deeplearning4j.nn.weights.WeightInit;
//import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
//import org.nd4j.linalg.dataset.DataSet;
//import org.nd4j.linalg.dataset.SplitTestAndTrain;
//import org.nd4j.linalg.lossfunctions.LossFunctions;
//import org.springframework.core.io.ClassPathResource;
//
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by denis on 30.12.15.
// */
//public class TimeSeriesPrediction {
//
////    public static void predict(List<StatisticModel> stats) throws IOException {
////        int lstmLayerSize = 200;
////        List<Co2> data = Arrays.asList(new Co2(840.0), new Co2(851.4), new Co2(861), new Co2(890), new Co2(914), new Co2(915), new Co2(930), new Co2(949));
////        RecordReader reader = new CollectionRecordReader((Collection<? extends Collection<Writable>>) data);
//////
////        DataSetIterator iter = new RecordReaderDataSetIterator(reader,null,data.size(),1,1,true);
//////        DataSet next = iter.next();
//////        next.normalizeZeroMeanZeroUnitVariance();
//////        SplitTestAndTrain testAndTrain = next.splitTestAndTrain(0.9);
//////        MultiLayerNetwork network = new MultiLayerNetwork(conf);
//////        network.init();
////        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
////                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT).iterations(1)
////                .learningRate(0.1)
////                .rmsDecay(0.95)
////                .seed(12345)
////                .regularization(true)
////                .l2(0.001)
////                .list(3)
////                .layer(0, new GravesLSTM.Builder().nIn(iter.inputColumns()).nOut(lstmLayerSize)
////                        .updater(Updater.RMSPROP)
////                        .activation("tanh").weightInit(WeightInit.DISTRIBUTION)
////                        .dist(new UniformDistribution(-0.08, 0.08)).build())
////                .layer(1, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
////                        .updater(Updater.RMSPROP)
////                        .activation("tanh").weightInit(WeightInit.DISTRIBUTION)
////                        .dist(new UniformDistribution(-0.08, 0.08)).build())
////                .layer(2, new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT).activation("softmax")        //MCXENT + softmax for classification
////                        .updater(Updater.RMSPROP)
////                        .nIn(lstmLayerSize).nOut(nOut).weightInit(WeightInit.DISTRIBUTION)
////                        .dist(new UniformDistribution(-0.08, 0.08)).build())
////                .pretrain(false).backprop(true)
////                .build();
////
////        MultiLayerNetwork net = new MultiLayerNetwork(conf);
////        net.init();
////        net.setListeners(new ScoreIterationListener(1));
////
////
////
////        int seed = 123;
////        int iterations = 100;
//////        RecordReader reader = new CSVRecordReader();
//////        try {
//////            reader.initialize(new FileSplit(new ClassPathResource("data.txt").getFile()));
//////        } catch (InterruptedException e) {
//////            e.printStackTrace();
//////        }
////        List<Co2> data = Arrays.asList(new Co2(840.0), new Co2(851.4), new Co2(861), new Co2(890), new Co2(914), new Co2(915), new Co2(930), new Co2(949));
////        RecordReader reader = new CollectionRecordReader((Collection<? extends Collection<Writable>>) data);
////        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
////                .seed(seed) // Seed to lock in weight initialization for tuning
////                .iterations(iterations) // # training iterations predict/classify & backprop
////                .learningRate(1e-3f) // Optimization step size
////                .optimizationAlgo(OptimizationAlgorithm.LBFGS) // Backprop method (calculate the gradients)
////                .gradientNormalization(GradientNormalization.RenormalizeL2PerLayer)
////                .l2(2e-4).regularization(true)
////                .list(1) // # NN layers (does not count input layer)
////                .layer(0, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
////                                .nIn(12) // # input nodes
////                                .nOut(1) // # output nodes
////                                .activation("identity")
////                                .weightInit(WeightInit.XAVIER)
////                                .build()
////                ) // NN layer type
////                .build();
////
////        DataSetIterator iter = new RecordReaderDataSetIterator(reader,null,data.size(),1,1,true);
////        DataSet next = iter.next();
////        next.normalizeZeroMeanZeroUnitVariance();
////        SplitTestAndTrain testAndTrain = next.splitTestAndTrain(0.9);
////        MultiLayerNetwork network = new MultiLayerNetwork(conf);
////        network.init();
////        network.setListeners(new ScoreIterationListener(1));
////        network.fit(testAndTrain.getTrain());
////
////
////    }
//
////    public void predict() {
////        try {
////            // path to the Australian wine data included with the time series forecasting
////            // package
////            String pathToWineData = weka.core.WekaPackageManager.PACKAGES_DIR.toString()
////                    + File.separator + "timeseriesForecasting" + File.separator + "sample-data"
////                    + File.separator + "wine.arff";
////
////            // load the wine data
////            Instances wine = new Instances(new BufferedReader(new FileReader(pathToWineData)));
////
////            // new forecaster
////            WekaForecaster forecaster = new WekaForecaster();
////
////            // set the targets we want to forecast. This method calls
////            // setFieldsToLag() on the lag maker object for us
////            forecaster.setFieldsToForecast("Fortified,Dry-white");
////
////            // default underlying classifier is SMOreg (SVM) - we'll use
////            // gaussian processes for regression instead
////            forecaster.setBaseForecaster(new GaussianProcesses());
////
////            forecaster.getTSLagMaker().setTimeStampField("Date"); // date time stamp
////            forecaster.getTSLagMaker().setMinLag(1);
////            forecaster.getTSLagMaker().setMaxLag(12); // monthly data
////
////            // add a month of the year indicator field
////            forecaster.getTSLagMaker().setAddMonthOfYear(true);
////
////            // add a quarter of the year indicator field
////            forecaster.getTSLagMaker().setAddQuarterOfYear(true);
////
////            // build the model
////            forecaster.buildForecaster(wine, System.out);
////
////            // prime the forecaster with enough recent historical data
////            // to cover up to the maximum lag. In our case, we could just supply
////            // the 12 most recent historical instances, as this covers our maximum
////            // lag period
////            forecaster.primeForecaster(wine);
////
////            // forecast for 12 units (months) beyond the end of the
////            // training data
////            List<List<NumericPrediction>> forecast = forecaster.forecast(12, System.out);
////
////            // output the predictions. Outer list is over the steps; inner list is over
////            // the targets
////            for (int i = 0; i < 12; i++) {
////                List<NumericPrediction> predsAtStep = forecast.get(i);
////                for (int j = 0; j < 2; j++) {
////                    NumericPrediction predForTarget = predsAtStep.get(j);
////                    System.out.print("" + predForTarget.predicted() + " ");
////                }
////                System.out.println();
////            }
////
////            // we can continue to use the trained forecaster for further forecasting
////            // by priming with the most recent historical data (as it becomes available).
////            // At some stage it becomes prudent to re-build the model using current
////            // historical data.
////
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////    }
//
//    public static void predict(List<StatisticModel> stats) throws IOException {
//        int seed = 123;
//        int iterations = 100;
////        RecordReader reader = new CSVRecordReader();
////        try {
////            reader.initialize(new FileSplit(new ClassPathResource("data.txt").getFile()));
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        List<Co2> data = Arrays.asList(new Co2(840.0), new Co2(851.4), new Co2(861), new Co2(890), new Co2(914), new Co2(915), new Co2(930), new Co2(949));
//        RecordReader reader = null; //new CollectionRecordReader();//(Collection<? extends Collection<Writable>>) data);
//        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
//                .seed(seed) // Seed to lock in weight initialization for tuning
//                .iterations(iterations) // # training iterations predict/classify & backprop
//                .learningRate(1e-3f) // Optimization step size
//                .optimizationAlgo(OptimizationAlgorithm.LBFGS) // Backprop method (calculate the gradients)
//                .gradientNormalization(GradientNormalization.RenormalizeL2PerLayer)
//                .l2(2e-4).regularization(true)
//                .list(1) // # NN layers (does not count input layer)
//                .layer(0, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
//                                .nIn(12) // # input nodes
//                                .nOut(1) // # output nodes
//                                .activation("identity")
//                                .weightInit(WeightInit.XAVIER)
//                                .build()
//                ) // NN layer type
//                .build();
//
//        DataSetIterator iter = new RecordReaderDataSetIterator(reader,null,data.size(),1,1,true);
//        DataSet next = iter.next();
//        next.normalizeZeroMeanZeroUnitVariance();
//        SplitTestAndTrain testAndTrain = next.splitTestAndTrain(0.9);
//        MultiLayerNetwork network = new MultiLayerNetwork(conf);
//        network.init();
//        network.setListeners(new ScoreIterationListener(1));
//        network.fit(testAndTrain.getTrain());
//
//
//    }
//
//    public static class Co2 implements Writable {
//        double co2;
//
//        public Co2(double co2) {
//            this.co2 = co2;
//        }
//
//        public double getCo2() {
//            return co2;
//        }
//
//        public void setCo2(double co2) {
//            this.co2 = co2;
//        }
//
//        @Override
//        public void write(DataOutput out) throws IOException {
//            out.writeDouble(co2);
//        }
//
//        @Override
//        public void readFields(DataInput in) throws IOException {
//            co2 = in.readDouble();
//        }
//
//        @Override
//        public double toDouble() {
//            return co2;
//        }
//
//        @Override
//        public float toFloat() {
//            return (float) (co2);
//        }
//
//        @Override
//        public int toInt() {
//            return 0;
//        }
//
//        @Override
//        public long toLong() {
//            return 0;
//        }
//    }
//}


