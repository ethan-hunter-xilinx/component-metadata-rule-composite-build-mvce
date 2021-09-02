# Component Metadata Rules with Composite Builds MVCE

This MVCE demonstrates issues with the use of component metadata rules during
composite builds.

In this example, the `consumer` project builds an Java application which depends on the `producer`
project (which is a library).

In this case, the `consumer` project adds a component metadata rule to all incoming components which adds an example capability . It requires this capability for its dependency on `producer`.

During a normal build (where `producer` is an external module), everything works as expected. During a composite build (where `producer` is from an included build), the component metadata rule is not applied and the build fails. 

## Expected Behavior

The `consumer` project should build successfully whether it's resolving `producer` as an external module or from an included build.

## Observed Behavior

The `consumer` build fails when `producer` is in an included build.

## Steps to Reproduce

### Successful Case

```
$ cd producer
$ ./gradlew publish
$ cd ../consumer
$ ./gradlew build
```

In this case the consumer build is successful which is the expected result.

```
BUILD SUCCESSFUL in 657ms
5 actionable tasks: 5 up-to-date
```

### Failure Case

```
$ cd consumer
$ ./gradlew build --include-build ../producer
```
This produces the following:
```
FAILURE: Build failed with an exception.

* What went wrong:
Could not determine the dependencies of task ':distTar'.
> Could not resolve all task dependencies for configuration ':runtimeClasspath'.
   > Could not resolve com.example:producer:1.
     Required by:
         project :
      > Unable to find a variant of project :producer providing the requested capability example:capability:1:
           - Variant apiElements provides com.example:producer:1
           - Variant runtimeElements provides com.example:producer:1
```

