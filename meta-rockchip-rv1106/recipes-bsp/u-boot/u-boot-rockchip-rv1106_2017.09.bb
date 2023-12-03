require recipes-bsp/u-boot/u-boot.inc

DESCRIPTION = "U-Boot with modifications for the Rockchip RV1106 SoC"
PROVIDES += "u-boot"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "\
    git://github.com/LuckfoxTECH/luckfox-pico;protocol=https;branch=main;subpath=sysdrv/source/uboot \
    file://env.txt \
    file://RV1106MINIALL.ini \
    file://relocated-environment.cfg \
"

SRCREV = "5532a62450068bb779b8b38706503433bf755fd1"

RM_WORK_EXCLUDE += "${PN} "

COMPATIBLE_MACHINE:cura = "(rockchip-rv1106)"

S = "${WORKDIR}/uboot/u-boot"
B = "${WORKDIR}/build"

EXTRA_OEMAKE += " KCFLAGS='-Wno-error=enum-int-mismatch -Wno-error=address -Wno-error=maybe-uninitialized' "
DEPENDS += "bc-native coreutils-native u-boot-tools-native"

inherit pkgconfig deploy

do_configure[cleandirs] = "${B}"

do_compile:append () {
    ${S}/../rkbin/tools/boot_merger ${WORKDIR}/RV1106MINIALL.ini
    mkenvimage -s 8192 -p 0x0 -o env.img ${WORKDIR}/env.txt
}

do_deploy:append () {
    install -D -m 0644 ${B}/rv1106_idblock.img ${DEPLOYDIR}/idblock.img
    install -D -m 0644 ${B}/env.img ${DEPLOYDIR}/u-boot-default-env.img
}
