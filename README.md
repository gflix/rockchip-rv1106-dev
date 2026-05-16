# rockchip-rv1106-dev

This repository aims to provide a BSP for the Rockchip RV1106 SoCs as found on the
LuckFox Pico (https://www.luckfox.com/Mini-PC/Luckfox-Pico).

The RV1106 is not yet very well documented. For example the actually required SD card layout differs from the
layout which gets burned by the GUI tools provided by Rockchip. Thus there is a lot of guesswork at the moment.

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

The SD card image includes the pre-boot loader, U-Boot, and a FIT image containing the Linux kernel along with
a minimal device tree. U-Boot carries a modified boot command that loads the FIT image from a fixed offset on
the SD card and boots it directly.

The U-Boot default environment includes a `boot_net` script that can be used to load and run
the FIT image from a TFTP server instead of the SD card.

```
=> run boot_net
```

## Known limitations

There is no root filesystem yet. The kernel will boot but stall waiting for the root device
specified in the device tree bootargs, which does not exist on the SD card.
