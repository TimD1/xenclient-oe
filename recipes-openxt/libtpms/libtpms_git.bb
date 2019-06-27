DESCRIPTION = "The libtpms library provides software emulation of a Trusted Platform Module (TPM 1.2 and TPM 2.0)"
LICENSE = "IBM-TCG"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e73f0786a936da3814896df06ad225a9"

DEPENDS = "automake autoconf libtool make gcc glibc openssl"

PV = "git${SRCPV}"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/stefanberger/libtpms.git;protocol=${OPENXT_GIT_PROTOCOL};branch=stable-0.6.0"

S = "${WORKDIR}/git"

inherit pkgconfig xenclient

do_configure() {
    ${S}/autogen.sh --with-tpm2 --with-openssl --prefix=/usr
}

do_compile() {
    make
}

do_install() {
    make install
}
