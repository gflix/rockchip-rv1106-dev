SUMMARY = "SD card image generator"
COMPATIBLE_MACHINE = "(rockchip-rv1106)"

inherit genimage

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += "file://genimage.config"

do_genimage[depends] += " \
    virtual/bootloader:do_deploy \
"
