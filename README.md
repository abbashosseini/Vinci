### Vinci Library For Image And File Caching and Image loading [![](https://jitpack.io/v/abbashosseini/Vinci.svg)](https://jitpack.io/#abbashosseini/Vinci)

>Vinci android library for Image Caching and Image Loading and its created multiThreading and make use of Concurrency for speedup the process

### About VinCi :

##### Min API 10+

its android library its get _URL_ or _URLS_ from the net and its do this concurrency so no need be worry about sharing resource and its cache / Download the file Synchronously for Now Of Course and later i replace it with Asynchronous NetWorking and another thing Vinci can save file in internal storage and you can get full path and where file can you find and use it for example you can put path (URI) in _database_ and you can now retrieve URI/PATH form database.

####For Reading :

you can display your **Photo** in ImageView/CustomImageView _(RoundedImage/HexagonImage)_ like this :

* **Download** :

```java

Vinci
        .base(context)
        .process()
        .load(uri)
        .putIn(viewHolder.imageView);

```

* **Bitmap**:

```java
  
  // #1 get Bitmap From URI
 Bitmap bitmap = Vinci
                .base(context)
                .process()
                .loadSynchronous(uri);

viewHolder.imageView.setImageBitmap(bitmap);
        
//  #2 get Bitmap from File

Bitmap bitmap = Vinci
                .base(context)
                .process()
                .decodeFile(file);

viewHolder.Writer.setImageBitmap(bitmap);
  
```



Store a **Files**  into **internalStorage**:

```java

String image = Vinci
		        .base(context)
		        .process()
		        .KeepIt();

Log.i(getClass().getSimpleName(), image);
```

Retrive a **File** From **internalStorage**:
```java

  	Drawable drawable = Vinci.base(getContext).storage().from(/*PATH*/);
  	imageView.setImageDrawable(drawable);

```

#### for multiThread, Concurrency and Safety you can use `SafeList` for more then one file :

```java

SafeList<Object> list = new SafeList<>();
//add objects
list.prepend(/* Objects */);
//take objects
list.top(/* get Objects */);

	

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

#### Add **aar** file locally  :

- [Download](https://github.com/abbashosseini/Vinci/blob/master/Vinci.aar?raw=true) AAR File .
- _go to_ **File** > **New Module** > **Import .JAR or .AAR Package**
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
