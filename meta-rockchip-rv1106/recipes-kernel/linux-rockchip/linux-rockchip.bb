SUMMARY = "Rockchip vendor kernel for RV1106"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel

COMPATIBLE_MACHINE = "rockchip-rv1106"

SRC_URI = " \
    git://github.com/rockchip-linux/kernel.git;protocol=https;branch=develop-5.10;destsuffix=${STAGING_KERNEL_DIR} \
"
SRCREV = "792a7d4273a59e80dafca48ba11438f43a6d8bda"

LINUX_VERSION ?= "5.10"
PV = "${LINUX_VERSION}+git"

KERNEL_CLASSES += "kernel-fit-extra-artifacts"

SRC_URI += "file://defconfig"

INSANE_SKIP:${PN}-src = "buildpaths"
