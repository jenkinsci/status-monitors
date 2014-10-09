# **Multimodular project "Jenkins applications"**

This project was developed as part of a bachelor thesis at the Munich University of Applied Sciences.
It was created as an example for a multimodulare software project.
It shows how to deal with an Android and a Java project in one multiproject and how to apply common configuration to
the projects.
It contains an Android application for displaying basic elements of a Jenkins build server and 
a Jenkins Tray Icon which notifies about finished builds.
Both applications are using a common webservice project.

## Simple Jenkins Monitor 
Jenkins Monitor is a small Android app which shows the jobs of a Jenkins build server with their current status. Furthermore you can access the builds of each job displaying timestamp, result, duration of the build.

## Jenkins Desktop Notifier
A tray icon developed with Swing, so it can be used on every system with a task bar. Checks the Jenkins server regularly for finished builds. If one build finished, it shows a notification.

## Jenkins REST Client
Small web library which is used by the applications above. Provides methods and tools for accessing Jenkins.

## How to use
For the deploying of the project you need the following things:

* Java SDK 7 or higher
* Android SDK with
	* Android SDK Build-tools 20
	* SDK Platform API 18
	* Android Support Repository

Gradle is used as build tool, but a wrapper script is provided, so no Gradle installation is needed to execute the
build.