# Vinci Library

![Alt text](https://jitpack.io/v/abbashosseini/Vinci.svg)
>Vinci android library for work and convert Images into Drawable, byteArray, Bitmap, Files 

### About VinCi :

its android library its get _URL_ and _download_ **image** also save it in internal storage, you can also save image in _database_ and you can retrieve image form database.
i try make it simple to _work_ with **Images** and mess with image any way you want convert it in ByteArray, Drawable, Bitmap and **ICON** but added in later and VinCi not try to say saving your Images in database is *good practice* _NO_ at all this usrful for some app like mine save user profile in he's device for what :

* you can use any URl from the net and use it in YOUR app just like that.
* Download any image with any **FORMAT** from  the net and put it in users device and use it or use it as Drawable , Files, ByteArray, Bitmap or vice versa .


#### Note :

  tested just on  one app for now, and its was part of my app i decided to put in here maybe was helpful .
  and feel free please add _**issues**_ .


####For Reading :

you can display your **Photo** in ImageView/CustomImageView _(RoundedImage/HexagonImage)_ like this :

* **Drawable** :
```java
  // Thats it
   new Vinci(context).AndDrawable(PhotoProcess.load(image))
  
  //Example :
  //and use it like this  - setImageDrawable - example:
  String image = "URL";
  ImageView.setImageDrawable(
                new Vinci(context).AndDrawable(
                        PhotoProcess.load(image)));
```

* **Bitmap**:

```java
   //support BitMap like this For now
  
  Bitmap bitmap = PhotoProcess.drawableToBitmap(
                        new Vinci(context).AndDrawable(
                            PhotoProcess.load(AxKarbar)
                        )
                );
ImageView.setImageBitmap(bitmap);
  
```

* **Byte Array**:

```java
   //support BitMap like this For now
  new Vinci(getContext).AndByte("Image URL from the net");
  
  //OR
  byte[] imageByte  = PhotoProcess.load("URL");
  //WhatEver
  
```


#### For Storing :


**Internal Storage** :
```java

  	new Vinci(context).intoStorage(
                            bitmapObject,
                            FileName
                    );

```


### ScreenShot and Custom ImageView:

- [x] You can Use **HexagonImage** Class : <br/>
Example:
  ```xml
    <mklib.hosseini.com.vinci.Shapes.HexagonImage
    android:layout_height="120sp"
    android:layout_width="120sp"
    android:id="@+id/Axkarbar"
    android:layout_gravity="center"
    android:scaleType="centerCrop"
    android:padding="4dp"
  
      />
```
  ![ScreenShot](https://github.com/abbashosseini/Vinci/blob/master/Image/P.jpg)

- [x] You can Use **RoundedImage** Class : <br/>

Example:
	
```xml
	      <mklib.hosseini.com.vinci.Shapes.RoundedImage
	      android:layout_height="120sp"
	      android:layout_width="120sp"
	      android:id="@+id/ID"
	      android:layout_gravity="center"
	      android:scaleType="centerCrop"
	      android:padding="4dp"
	      
	      />
```

![ScreenShot](https://github.com/abbashosseini/Vinci/blob/master/Image/L.jpg)
  
  
####Gradle:
  
  
  
  
  ```gradle
	  allprojects {
		repositories {
			...
			// In Build.gradle in Root Folder
			maven { url "https://jitpack.io" }
		}
	}
```
```gradle	
	// In Build.gradle in App Folder
	dependencies {
	        compile 'com.github.abbashosseini:Vinci:-SNAPSHOT'
	}
  
  ````
  
####Maven :

```xml
	<!-- Step One  -->
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
	<!-- Step TWO  -->
	
	<dependency>
	    <groupId>com.github.abbashosseini</groupId>
	    <artifactId>Vinci</artifactId>
	    <version>-SNAPSHOT</version>
	</dependency>

```

###License

	Copyright (C) 2015 AbbasHosseini
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
