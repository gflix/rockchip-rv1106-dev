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

When finished the SD card image can be found at `build/deploy/images/rockchip-rv1106/sdcard-rockchip-rv1106.img`.

Write the image to an empty SD card, i.e:

```
$ dd if=build/deploy/images/rockchip-rv1106/sdcard-rockchip-rv1106.img of=/dev/<path_to_sd_card> bs=4M oflag=sync
```

The SoC only boots from SD card, when when SPI does not have a bootable image or if the SPI flash is not deployed
to the PCB (i.e. Luckfox Pico Mini without Flash).

## Known limitations

Currently the kernel is not integrated so the SD card image only includes the pre-boot loader and U-Boot.
