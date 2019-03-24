# protected_deserialization

## Brief
This project is trying to provide a way to deserialize java objects safely. 

## Background
I started working on my leisure time in the middle of year 2018. Java deserialization vulnerabilities become a big hit to security since 2016 and rougly speaking, more than half of the java vulnerabilities reported recently are relative to deserialization. If there are 3rd party libraries in your code with deserialization vulnerability, even though you didn't use your code, they can be exploited. For why it is so easy to bexploited, there are many resounce in the web. Or, you can look at these two links for quick explanation. 

- https://diablohorn.com/2017/09/09/understanding-practicing-java-deserialization-exploits/  
- https://www.slideshare.net/codewhitesec/exploiting-deserialization-vulnerabilities-in-java-54707478 


## Approach
Because when a java object is to be deserialized, the class which it is intended to be casted to is usually known in advance. My code will check if serialized data matches to the intended class or not, either by being the same class or a subclass or an implemention. 

So far, it can show that this method can be used to protect deserialization in a general approach. 

## Future
I would like to find out the intended class-to-be-casted from legacy 3rd code and apply this checking automatically. I hope I can find the time to do so. If you are interested in this project, pls feel free to contact me.


## Contact
George Wen (wen_yuhui@hotmail.com)

