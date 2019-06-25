DESCRIPTION = "Libtpms-based TPM emulator with socket, character device, and Linux CUSE interface."
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8092c832b71ef20dfe4c6d3decb3a8"

DEPENDS = "devscripts equivs build-essential libtasn1-dev pkg-config automake autoconf bash coreutils expect libtool sed fuse net-tools python3 python3-twisted_19.2.1 trousers tpm-tools socat libseccomp-dev libfuse-dev libglib2.0-dev"
DEPENDS += "libtpms"

PV = "0+git${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/stefanberger/swtpm.git;protocol=${OPENXT_GIT_PROTOCOL};branch=stable-0.1.0"

S = "${WORKDIR}/git/swtpm"

inherit autotools-brokensep pkgconfig xenclient

EXTRA_OECONF = "--prefix=/usr --with-openssl --with-cuse"
