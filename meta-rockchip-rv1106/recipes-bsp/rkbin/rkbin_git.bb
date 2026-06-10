DESCRIPTION = "Rockchip binaries"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "\
    git://github.com/LuckfoxTECH/luckfox-pico;protocol=https;branch=main;subpath=sysdrv/source/uboot/rkbin \
"

SRCREV = "8f34c2760d53de28f451b49d656df488a7b6ed96"

RM_WORK_EXCLUDE += "${PN} "

S = "${UNPACKDIR}/${BPN}"

inherit deploy nopackages

DDR_BLOB = "bin/rv11/rv1106_ddr_924MHz_v1.15.bin"

do_deploy () {
    echo "do_deploy()"

    install -d ${DEPLOYDIR}/rockchip
    install ${S}/${DDR_BLOB} ${DEPLOYDIR}/rockchip
    ln -s $(basename ${DDR_BLOB}) ${DEPLOYDIR}/rockchip/rv1106_ddr.bin
}

addtask deploy after do_configure before do_build

do_patch[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
deltask do_populate_sysroot
