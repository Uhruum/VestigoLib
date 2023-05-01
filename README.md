# Vestigo Lib

Is a spring boot library for counting vowels and consonants. Main service is
**LetterCounterService**.

* Supported string formats are : **PlainText, XML, JSON, YAML**.

When reading those formats next rules are applied:
* **PlainText** -- whole text is considered for counting both vowels and consonants
* **XML**
for counting consonants whole text is taken and
for counting vowels only values of attributes and elements are taken in consideration

* **JSON**
for counting consonants whole text is taken and
for counting vowels only values of properties are taken in consideration

* **YAML**
for counting consonants whole text is taken and
for counting vowels only values of properties are taken in consideration

### Distribution
*In this case of distribution we will only show how to build and add this project to local maven repository with maven*

In maven window open Lifecycle and then :
1. run clean
2. run compile
3. run test
4. run package
5. run install

After step 5 app package can be found in your local maven repository. To test is open new maven project
add to *dependencies* section of **pom.xml** :

`<dependency>
<groupId>vestigo</groupId>
<artifactId>VestigoLib</artifactId>
<version>1.0-SNAPSHOT</version>
</dependency>
`
Reload maven dependencies, and you should be able to call **LetterCounterService**.

### Run
You can specify VM option -Dtest.prop=\"quoted_value\" this will change default value of
app prop test.prop and it will use its value for input string when counting letters.