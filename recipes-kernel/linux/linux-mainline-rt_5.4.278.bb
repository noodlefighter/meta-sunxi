
DESCRIPTION = "Mainline RT Linux kernel"

SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"
COMPATIBLE_MACHINE = "(sun4i|sun5i|sun7i|sun8i|sun50i)"

inherit kernel

require linux.inc

# Since we're not using git, this doesn't make a difference, but we need to fill
# in something or kernel-yocto.bbclass will fail.
KBRANCH ?= "master"

DEPENDS += "rsync-native"

# Pull in the devicetree files into the rootfs
RDEPENDS_${KERNEL_PACKAGE_NAME}-base += "kernel-devicetree"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

S = "${WORKDIR}/linux-${PV}"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/rt/linux-stable-rt.git;branch=v5.4-rt;protocol=git \
	file://defconfig \
"

SRC_URI_append_use-mailine-graphics = " file://drm.cfg"
SRC_URI_append_bananapi = " file://axp20x.cfg"

FILES_${KERNEL_PACKAGE_NAME}-base_append = " ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/modules.builtin.modinfo"

SRCREV_pn-${PN} = "7779a526bf00deab50f6c3435b2049c3df943e02"
