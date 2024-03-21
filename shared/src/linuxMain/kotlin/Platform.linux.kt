class LinuxPlatform : Platform {
    override val name: String = "LinuxPlatform"
}

actual fun getPlatform(): Platform = LinuxPlatform()
