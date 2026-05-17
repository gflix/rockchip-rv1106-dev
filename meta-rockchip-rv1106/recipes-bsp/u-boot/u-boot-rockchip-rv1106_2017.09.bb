require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRCREV = "c98ac3487e413c71e5d36322ef3324b21c6f60f9"

SRC_URI:append = "\
    file://0001-Imported-full-set-of-Rockchip-s-modifications.patch \
    file://0001-Override-RV1106-boot-command.patch \
    file://env.txt \
    file://RV1106MINIALL.ini \
    file://relocated-environment.cfg \
    file://configure-features.cfg \
"

PATCHTOOL = "git"

EXTRA_OEMAKE:append = "\
    KCFLAGS='-Wno-error=enum-int-mismatch -Wno-error=address -Wno-error=maybe-uninitialized' \
"
DEPENDS:append = "\
    bc-native \
    coreutils-native \
    rockchip-rkbin \
    rockchip-rkbin-native \
    u-boot-tools-native \
"

do_patch_post () {
    sed -i -e "s|@DEPLOY_DIR_IMAGE@|${DEPLOY_DIR_IMAGE}|g" -e "s|@BUILD_DIR@|${B}|g" ${UNPACKDIR}/RV1106MINIALL.ini
}
addtask patch_post after do_patch before do_configure

do_compile:append () {
    boot_merger ${UNPACKDIR}/RV1106MINIALL.ini
    mkenvimage -s 8192 -p 0x0 -o env.img ${UNPACKDIR}/env.txt
}

do_deploy:append () {
    install -D -m 0644 ${B}/rv1106_idblock.img ${DEPLOYDIR}/idblock.img
    install -D -m 0644 ${B}/env.img ${DEPLOYDIR}/u-boot-default-env.img
}
