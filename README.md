android-image-loader
====================

## Android Image Loader for ListView

<div align="center">
  <img src="https://raw.github.com/dmytrodanylyk/android-image-loader/master/diagram.png"/>
</div>

### Task
We have a ListView of images which must be downloaded and displayed.

####Pitfalls

####Spawn Thread for every image
1. If user scroll down fast will slow down image loading
2. If user scroll up then down may cause <a href="http://docs.oracle.com/javase/1.5.0/docs/api/java/util/ConcurrentModificationException.html" target="_blank">ConcurrentModificationException</a>
3. If ListView contains big among of data may cause <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/OutOfMemoryError.html" target="_blank">OOME</a>

#### Spawn AsyncTask for every image
1. Works differently in various Android versions
2. Will cause crash when reach maximum pool size which equals to 128

#### Fixed Thread Pool Executor
1. If user scroll down fast cause delay (new images will not be downloaded before previous are downloaded)
2. If user repeat up/down scrolling queue could grow large enough to cause <a href="http://docs.oracle.com/javase/7/docs/api/java/lang/OutOfMemoryError.html" target="_blank">OOME</a>

#### Requirements to Android Image Loader
1. Ability to use any executor
2. Provide skipping scrolled images mechanism
3. Distinguish image download code from image loader (url may require specific headers or cookies, this should handle client)
4. Ability to easily attach Disk / Memory cache