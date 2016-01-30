# Vinci Library
>Vinci android library for get url and  save it Or Display the Photo From  DB

### About VinCi :

its android library its get _URL_ and _download_ **image** also save it in _database_ and i testedjust on  one app for now.

####For Reading :

Example :

```java
  //Get photo from Database
  byte[] ByteImage = cursor.getBlob(cursor.getColumnIndex(ColumnsName.IMAGE))
```
and now you can display your **Photo** in ImageView/CustomImageView _(RoundedImage/HexagonImage)_ like this :

```java
  // Thats it
  new Vinci(context).PhotoFromDB(ByteImage)
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
  ![ScreenShot](https://github.com/abbashosseini/Vinci/blob/master/Image/L.png)

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
  ![ScreenShot](https://github.com/abbashosseini/Vinci/blob/master/Image/P.png)
  
  

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
