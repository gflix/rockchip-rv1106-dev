SUMMARY = "Platform test image"

inherit core-image

IMAGE_FSTYPES = "cpio.xz"
IMAGE_NAME_SUFFIX ?= ""

# IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL:append = " \
    packagegroup-platform-test \
"
