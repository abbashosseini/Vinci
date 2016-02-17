# Vinci Library

[![](https://jitpack.io/v/abbashosseini/Vinci.svg)](https://jitpack.io/#abbashosseini/Vinci)
>Vinci android library for work and convert Images into Drawable, byteArray, Bitmap, Files and easy Internal Storage Task and get URI if you like Put it in databasec or just displayed on ImageView.

### About VinCi :

its android library its get _URL_ and valid one of cource and _download_ **image** also save it in internal storage and you can put it any where you like and  you can also save image in _database_ and you can retrieve image form database.
i try make it simple to _work_ with **Images** and mess with images or convert it in ByteArray, Drawable, Bitmap and **ICON** but added in later and VinCi not try to say saving your Images in database is *good practice* _NO_ at all.

#### Can Do :

* you can use any URl from the net and use it in YOUR app just like that.
* You can display you image you have ot user have on imageView.
* Download any image with any **FORMAT** from  the net and put it in users device and use it or use it as Drawable , Files, ByteArray, Bitmap or vice versa .


####For Reading :

you can display your **Photo** in ImageView/CustomImageView _(RoundedImage/HexagonImage)_ like this :

* **Download** :

```java
Load load = new Load(new ResultProcess() {
    @Override
    public void onFinish(byte[] output) {
    //get Image file as byte
});

load.execute("URL");

//OR implements  Activity or non-Activity class or use it in its own class 


```

* **Drawable** :
```java
  // Thats it
   //new Vinci(context).AndDrawable(ByteArray)
  
  //Example :
  //and use it like this  - setImageDrawable - example:
  
  Drawable drawable = new Vinci(context).AndDrawable(
					  //byteArray
					  );
					  
  ImageView.setImageDrawable(drawable);
```

* **Bitmap**:

```java
  
  Bitmap bitmap = new Vinci(context).drawableToBitmap(
  			//Drawable
                );
                
ImageView.setImageBitmap(bitmap);
  
```



#### For Storing :


**Internal Storage** :
```java

  	String PathOnStorage  = new Vinci(context).intoStorage(
  			/*pass your file name here With extension of cource*/
  			String fileName, 
  			/* if for not like default PATH ( /storage/emulated/0/Vinci/Pictures ) change it here*/
  			String CustomPATH, 
  			/* this pas you pas number between 1 , 10 
  			   for size 10 highest less then 10 image
  			   become les quality and size of cource*/
  			int Quality
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
	        compile 'com.github.abbashosseini:Vinci:ed6b74ebcc'
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
	    <version>ed6b74ebcc</version>
	</dependency>

```

#OR

i can't put it in **JCenter** / **Bintary** Right now ! so

#### Add **aar** file locally  :

- [Download](https://github.com/abbashosseini/Vinci/blob/master/Vinci-release.aar?raw=true) AAR File .
- _go to file > New Module > Import .JAR or .AAR Package_
- Imported and in **dependencies** add :

 ```gradle	
 
	compile project(':Vinci-release')		

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
