SUMMARY = "RV1106 - Platform test environment"

inherit packagegroup

RDEPENDS:${PN} = "\
    strace \
    util-linux-dmesg \
"
