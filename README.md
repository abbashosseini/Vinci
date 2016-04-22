### Vinci Library For Image Caching and Image loading [![](https://jitpack.io/v/abbashosseini/Vinci.svg)](https://jitpack.io/#abbashosseini/Vinci)

> Vinci android library for Image Caching and Image Loading and its support multiThreading and make use of Concurrency for speedup the process for more, read [WIKI](https://github.com/abbashosseini/Vinci/wiki)

### About VinCi :

##### Min API 10+

its android library its get _URL_ or _URLS_ from the net and its do this concurrency so no need be worry about sharing resource and its cache / Download the file Synchronously for Now Of Course and later i replace it with Asynchronous NetWorking and another thing Vinci can save file in internal storage and you can get full path and where file can you find and use it for example you can put path (URI) in _database_ and you can now retrieve URI/PATH form database.

  
####Gradle:
  
  
  
  
  ```gradle
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
```gradle	
	dependencies {
	        compile 'com.github.abbashosseini:Vinci:aar'
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
	    <version>aar</version>
	</dependency>



```



#OR

#### Add **aar** file locally  :

- [Download](https://github.com/abbashosseini/Vinci/releases/download/aar/Vinci-release.aar) AAR File .
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
