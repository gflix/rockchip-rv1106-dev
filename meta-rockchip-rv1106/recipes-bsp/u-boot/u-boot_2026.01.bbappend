FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:rockchip-rv1106 = "\
    file://0001-Added-basic-support-for-Rockchip-RV1106.patch \
"

DEPENDS:append = "\
    rkbin \
"

do_provide_blobs () {
    cp -v ${DEPLOY_DIR_IMAGE}/rockchip/rv1106_ddr.bin ${B}
}

addtask provide_blobs after do_configure before do_build

EXTRA_OEMAKE:append:rockchip-rv1106 = "\
    ROCKCHIP_TPL=${B}/rv1106_ddr.bin \
"

do_deploy:append:rockchip-rv1106 () {
    install -m 664 ${B}/idbloader.img ${DEPLOYDIR}
}
