Scala project for SessionCam

-Read multiple JSON files - JSON will contain some key, some date and other data<br />
-Using POJO, save JSON data into a collection of type<X><br />
-Use value from JSON as key, aggregate data using this key<br />
-Manipulate the Date within JSON - convert from GMT to UTC or similar for output<br />
-Write the aggregated updated data to a file<br />

-Should include logging<br />
-Should include unit tests<br />


mvn org.apache.maven.plugins:maven-archetype-plugin:1.0-alpha-7:create \
-DarchetypeGroupId=org.scala-tools.archetypes \
-DarchetypeArtifactId=scala-archetype-simple \
-DarchetypeVersion=1.2 \
-DremoteRepositories=http://scala-tools.org/repo-releases \
-DgroupId=com.rps -DartifactId=scalatest

mvn org.apache.maven.plugins:maven-archetype-plugin:1.0-alpha-7:create \
    -DarchetypeGroupId=org.scala-tools.archetypes \
    -DarchetypeArtifactId=scala-archetype-simple \
    -DarchetypeVersion=1.2 \
    -DremoteRepositories=http://scala-tools.org/repo-releases \
    -DgroupId=com.sessioncam \
    -DartifactId=sessioncam