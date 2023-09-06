package com.example.elemental.extension

    /*
        Test Dummy Data
        2275687L
        2351510L
        2144625L
        2182889L
        2091264L
        2124469L
        2089422L
        559826L
     */
fun Long.getFileSize(): String {
    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    var size = this.toDouble()
    var unitIndex = 0
    while (size >= 1024 && unitIndex < units.size - 1) {
        size /= 1024
        unitIndex++
    }
    return "%.2f%s".format(size, units[unitIndex])
}