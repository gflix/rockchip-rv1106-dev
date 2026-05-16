SUMMARY = "SD card image for Rockchip RV1106"
COMPATIBLE_MACHINE = "(rockchip-rv1106)"

IMAGE_FSTYPES = "wic"
WKS_FILE = "rockchip-rv1106.wks"
WKS_FILE_DEPENDS = "virtual/bootloader linux-yocto-fitimage"

inherit image

IMAGE_INSTALL = ""

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
