# bundle-management

This spring-boot project provides Java resource bundles(i18n) that can be saved to your prefered database and load back
It is kept simple. It is derived from the github
project [resource-bundles-data](https://github.com/lightblueseas/resource-bundles-data)

# ERD-Diagramm

The erd-diagramm for this database looks as
follows: ![erd-diagramm](https://github.com/astrapi69/bundle-management/blob/master/src/main/resources/erd/erd-diagram-resourcesbundles.png)

This erd-diagramm was created with [intellij](https://www.jetbrains.com/)

## License

The source code comes under the liberal MIT License, making bundle-management great for all types of I18N applications.

## Want to Help and improve it? ###

The source code for bundle-management are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/bundle-management/fork](https://github.com/astrapi69/bundle-management/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/bundle-management/pull/new/master).

Don't forget to add new units tests on your changes.

## Contacting the Developer

Do not hesitate to contact the bundle-management developers with your questions, concerns, comments, bug reports, or
feature requests.

- Feature requests, questions and bug reports can be reported at
  the [issues page](https://github.com/astrapi69/bundle-management/issues).

## Note

No animals were harmed in the making of this library.

# Donations

This project is kept as an open source product and relies on contributions to remain being developed. If you like this
project, please consider a donation through
paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B37J9DZF6G9ZC" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

1Jzso5h7U82QCNmgxxSCya1yUK7UVcSXsW

or over ether with:

0xaB6EaE10F352268B0CA672Dd6e999C86344D49D8

or over
flattr: <a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Fastrapi69%2Fbundle-management" target="_blank">
<img src="http://button.flattr.com/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0">
</a>

# Testing

Preconditions before start of the unit tests is to install docker on your system. The prefered way for ubuntu is
descripted [here](https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository)
After install you have to restart your system

If you get an exception by unit tests on ubuntu like this

```shell
[ers-lifecycle-0] tAndSystemPropertyClientProviderStrategy : ping failed with configuration Environment variables, system properties and defaults. Resolved dockerHost=unix:///var/run/docker.sock due to org.rnorth.ducttape.TimeoutException: Timeout waiting for result with exception

org.rnorth.ducttape.TimeoutException: Timeout waiting for result with exception

...

Caused by: java.lang.RuntimeException: java.io.IOException: native connect() failed : Permission denied
```

You can try this.
[official website description](https://docs.docker.com/engine/install/linux-postinstall/)

if this did not help you have to grand permissions to the docker.sock with the following command (use this with
caution!!!)

```shell
sudo chmod 666 /var/run/docker.sock
```


