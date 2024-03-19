class MingwX64Platform : Platform {
    override val name: String = "MingwX64Platform"
}

actual fun getPlatform(): Platform = MingwX64Platform()
