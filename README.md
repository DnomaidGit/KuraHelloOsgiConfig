***
# KuraHelloOsgiConfig
This is a simple example of an OSGI project for Eclipse Kura.
***
## Development Environment
* JVM (Java JDK SE 8 or Open JDK 8)
* Eclipse Neon
    ** An additional plugin, mToolkit, is needed to allow remote connectivity to an OSGi framework on a Kura-enabled target device. To install mToolkit into Eclipse, use the following steps:
    ** Open the Help | Install New Software… menu.
    ** Add the following URL as an update site based on your version of Eclipse        
        ** Eclipse Neon: http://mtoolkit-neon.s3-website-us-east-1.amazonaws.com
    ** Install the “mToolkit” feature (you need to uncheck the Group items by category checkbox in order to see the feature)
    ** Restart Eclipse. In the menu Window | Show View | Other, there should be an mToolkit | Frameworks option. If so, the plugin has been installed correctly.
* Debian 10
***
## Tested
* Raspberry pi 3 and 4
* Eclipse Kura 4.1.0.
***
## Build sources
The only requisite to build from sources is an already  
installed [Eclipse Kura User Workspace](https://www.eclipse.org/kura/downloads.php).  
***  
## Binary package
The binary package ready for the installation can be
found in folder "**resources/dp**".
***
## Links
Eclipse Kura Documentation:
* http://eclipse.github.io/kura/
* https://download.eclipse.org/kura/docs/api/
* https://docs.osgi.org/specification/
***
