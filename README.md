# rockchip-rv1106-dev

This repository aims to provide a BSP for the Rockchip RV1106 SoCs as found on the
LuckFox Pico (https://www.luckfox.com/Mini-PC/Luckfox-Pico).

The RV1106 is not yet very well documented. For example the actually required SD card layout differs from the
layout which gets burned by the GUI tools provided by Rockchip. Thus there is a lot of guesswork at the moment.

U-Boot was only provided as v2017.11 . The essential sources to allow booting from SD card and/or TFTP have
been ported to v2026.01 (native version of Yocto 6.0), but they still need some effort to get environment
running again, suitable bootscripts and if wanted drivers to support the SPI flash and booting from SPI flash.

The kernel is still at 5.10.x as newer versions did not boot yet.

## Usage

To build a bootable SD card image use to shipped KAS (https://pypi.org/project/kas/) control file from
an empty directory outside this tree:

```
$ kas build ../rockchip-rv1106-dev/kas-rockchip-rv1106-dev.yml
```

When finished the SD card image can be found at `build/deploy/images/rockchip-rv1106/sdcard-rockchip-rv1106.rootfs.wic`.

Write the image to an empty SD card, i.e:

```
$ dd if=build/deploy/images/rockchip-rv1106/sdcard-rockchip-rv1106.rootfs.wic of=/dev/<path_to_sd_card> bs=4M oflag=sync
```

The SoC only boots from SD card, when when SPI does not have a bootable image or if the SPI flash is not deployed
to the PCB (i.e. Luckfox Pico Mini without Flash).

## Current state

The SD card image includes the pre-boot loader, U-Boot, and a FIT image containing the Linux kernel,
a minimal device tree, and a small initramfs root filesystem. ~~~U-Boot carries a modified boot command
that loads the FIT image from a fixed offset on the SD card and boots it directly.~~~ At the moment there is no
environment and thus booting happens by manually entering either the command for booting from SD card or
via TFTP:

SD-Card:

```
read mmc 1:0 ${loadaddr} 1000 3000 && bootm ${loadaddr}
```

DHCP+TFTP:

```
dhcp ${loadaddr} fitimage-rv1106 && bootm ${loadaddr}
```

## Known limitations

The Linux kernel from Rockchip's `develop-5.10` branch was the last known working version.
Newer branches (`develop-6.1`, `develop-6.6`) have been tested but did not boot successfully.
