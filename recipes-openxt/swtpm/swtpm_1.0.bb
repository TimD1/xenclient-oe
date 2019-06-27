DESCRIPTION = "Libtpms-based software TPM emulator with socket, character device, and Linux CUSE interface."
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fe8092c832b71ef20dfe4c6d3decb3a8"
SECTION = "apps"

DEPENDS += "libtasn1 fuse glib-2.0 libtpms libtpms-native"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/stefanberger/swtpm.git;protocol=${OPENXT_GIT_PROTOCOL};branch=stable-0.1.0"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig xenclient
PARALLEL_MAKE = ""

TSS_USER="tss"
TSS_GROUP="tss"

PACKAGECONFIG ?= "openssl"
#PACKAGECONFIG += "${@bb.utils.contains('DISTRO_FEATURES', 'selinux', 'selinux', '', d)}"
PACKAGECONFIG[openssl] = "--with-openssl, --without-openssl, openssl"
#PACKAGECONFIG[gnutls] = "--with-gnutls, --without-gnutls, gnutls"
#PACKAGECONFIG[selinux] = "--with-selinux, --without-selinux, libselinux"

EXTRA_OECONF += "--prefix=/usr --with-openssl --with-cuse"
EXTRA_OECONF += "--with-tss-user=${TSS_USER} --with-tss-group=${TSS_GROUP}"

export SEARCH_DIR = "${STAGING_LIBDIR_NATIVE}"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system ${TSS_USER}"
USERADD_PARAM_${PN} = "--system -g ${TSS_GROUP} --home-dir  \
    --no-create-home  --shell /bin/false ${BPN}"

RDEPENDS_${PN} = "libtpms expect socat bash"
