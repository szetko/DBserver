TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh screen-cleanup plymouth-log apparmor x11-common udev keyboard-setup mountdevsubfs.sh procps cryptdisks cryptdisks-early open-iscsi networking iscsid urandom hwclock.sh rpcbind checkroot.sh lvm2 checkfs.sh kmod mountall.sh checkroot-bootclean.sh bootmisc.sh mountnfs-bootclean.sh mountnfs.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
open-iscsi: networking iscsid
networking: resolvconf mountkernfs.sh urandom procps
iscsid: networking
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
rpcbind: networking
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks checkroot.sh lvm2
kmod: checkroot.sh
mountall.sh: lvm2 checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
bootmisc.sh: checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh udev
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking rpcbind
mountall-bootclean.sh: mountall.sh
