DESCRIPTION = "Rockchip firmware binaries"

LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://LICENSE;md5=15faa4a01e7eb0f5d33f9f2bcc7bff62"

SRC_URI = "\
    git://github.com/LuckfoxTECH/luckfox-pico;protocol=https;branch=main;subpath=sysdrv/source/uboot/rkbin \
"

SRCREV = "824b817f889c2cbff1d48fcdb18ab494a68f69d1"

inherit deploy

BBCLASSEXTEND = "native"

S = "${UNPACKDIR}/rkbin"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install:class-native() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/tools/boot_merger ${D}${bindir}/boot_merger
}

do_deploy() {
    install -d ${DEPLOYDIR}/rockchip
    install -m 0644 ${S}/bin/rv11/rv1106_ddr_924MHz_v1.15.bin ${DEPLOYDIR}/rockchip
    ln -s rv1106_ddr_924MHz_v1.15.bin ${DEPLOYDIR}/rockchip/rv1106_ddr_924MHz.bin
    install -m 0644 ${S}/bin/rv11/rv1106_usbplug_v1.09.bin ${DEPLOYDIR}/rockchip
    ln -s rv1106_usbplug_v1.09.bin ${DEPLOYDIR}/rockchip/rv1106_usbplug.bin
}
do_deploy:class-native() {
    :
}
addtask deploy after do_install
