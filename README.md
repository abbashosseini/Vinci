# Vinci Library

![Alt text](https://jitpack.io/v/abbashosseini/Vinci.svg)
>Vinci android library for get url and  save it Or Display the Photo From  DB

### About VinCi :

its android library its get _URL_ and _download_ **image** also save it in _database_ and you can retrieve image form database i try make it simple to _work_ with **drawable and Bitmap** for example Work with this Objects is hard in **PICASSO** Library and VinCi not try to say saving your Images in database is *good practice* _NO_ at all this usrful for some app like mine save user profile in he's device for what :

* easy way to Handle user
* no need for RESTful Request to read everyTime (caching not solution for me Sorry)
* **Important** handle user activity when is not Connected or user in (**OfflineMod**) when he connected you can notice what he change and you wanna allow or not.....

i'm adding these futurs SOON but for now just wanna say what is **IDEA** Vinci Have.

#### Note :

  tested just on  one app for now, and its was part of my app i decided to put in here maybe was helpful .
  and feel free please add **issues** .

#### this library good for: 

This library good for thats applications have online communication with server all the time for offline mod need put some server side into in user device. 

####For Reading :

Example :

```java
  //Get photo from Database
  byte[] ByteImage = cursor.getBlob(cursor.getColumnIndex(ColumnsName.IMAGE))
```
and now you can display your **Photo** in ImageView/CustomImageView _(RoundedImage/HexagonImage)_ like this :
* **Drawable** :
```java
  // Thats it
  new Vinci(context).PhotoFromDB(ByteImage)
  //and use it like this  - setImageDrawable :
  ImageViewID.setImageDrawable(new Vinci(context).PhotoFromDB(ByteImage));
```

* **Bitmap**:

```java
   //support BitMap like this For now
  ImageView.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), Drawable));
  
```


#### For Storing :


```java

  byte[] BitmapToByte = new Vinci(getContext).PhotoInDB(URL);

```

and now just put **BitmapToByte** varibale to **BLOB** _Field_ like this:

```java
  ContentValues values = new ContentValues();
  values.put(ColumnsName.KARBAR_IMAGE, BitmapToByte);

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
