FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE:rockchip-rv1106 = "(rockchip-rv1106)"

SRC_URI:append:rockchip-rv1106 = "\
    file://0001-arm-rockchip-add-minimal-RV1106-support.patch \
    file://defconfig \
"

KBUILD_DEFCONFIG:rockchip-rv1106 = ""
KCONFIG_MODE:rockchip-rv1106 = "allnoconfig"

KERNEL_CLASSES += "kernel-fit-extra-artifacts"
