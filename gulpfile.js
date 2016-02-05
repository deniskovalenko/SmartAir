/**
 * Created by dmitry on 05.02.16.
 */

'use strict';

var gulp = require('gulp'),
    notify = require('gulp-notify'),
    less = require('gulp-less'),
    sass = require('gulp-sass'),
    mincss = require('gulp-minify-css'),
    concat = require('gulp-concat'),
    order = require('gulp-order'),
    sourcemaps = require('gulp-sourcemaps'),
    gulpIf = require('gulp-if'),
    debug = require('gulp-debug');

var isDevelopment = true;

var paths = {
    src: 'src/main/webapp/resources/index'
};

gulp.task('less', function () {
    return gulp.src(paths.src + '/styles/less/creative.less')
        .pipe(gulpIf(isDevelopment, sourcemaps.init()))
        .pipe(less())
        .pipe(gulpIf(isDevelopment, sourcemaps.write()))
        .pipe(gulp.dest(paths.src + '/styles/css/'));
});

gulp.task('sass', function () {
    return gulp.src(paths.src + '/styles/scss/**/*.scss')
       .pipe(sass())
       .pipe(gulp.dest(paths.src + '/styles/css/'));
});

gulp.task('styles', ['less', 'sass'], function () {
    return gulp.src(paths.src + '/styles/css/**/*.css')
        .pipe(concat('styles.css'))
        .pipe(mincss())
        .pipe(gulp.dest(paths.src + '/styles/'));
});

gulp.task('styles:login', function () {
    return gulp.src([
        paths.src + '/styles/css/bootstrap.min.css',
        paths.src + '/styles/css/stylesheetOverBootstrap2.css',
        paths.src + '/styles/css/wink.css'
    ])
        .pipe(order([
            'bootstrap.min.css',
            'wink.css',
            'stylesheetOverBootstrap2.css'
        ]))
        .pipe(gulpIf(isDevelopment, sourcemaps.init()))
        .pipe(debug())
        .pipe(concat('styles-login.css'))
        .pipe(mincss())
        .pipe(gulpIf(isDevelopment, sourcemaps.write()))
        .pipe(gulp.dest(paths.src + '/styles/'));
});

gulp.task('default', function () {
    gulp.src('/')
        .pipe(notify('Default Gulp task is running'));
});