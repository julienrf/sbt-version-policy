ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "com.example"
// Before the first release, set it to `None` because there is no previous release to compare to
ThisBuild / versionPolicyIntention := Compatibility.None
// Don’t check dependencies to internal modules when the version is like `1.2.3+4-abcd1234` (which
// is typically the version value generated by sbt-dynver in-between releases)
ThisBuild / versionPolicyIgnoredInternalDependencyVersions := Some("^\\d+\\.\\d+\\.\\d+\\+\\d+".r)

val a =
  project
    .settings(
      name := "dynver-test-a"
    )

val b =
  project
    .settings(
      name := "dynver-test-b",
    )
    .dependsOn(a)

val root =
  project.in(file("."))
    .aggregate(a, b)
    .settings(
      publish / skip := true
    )
