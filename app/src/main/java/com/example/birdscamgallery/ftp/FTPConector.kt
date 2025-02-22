import org.apache.commons.net.ftp.FTP
import org.apache.commons.net.ftp.FTPClient
import java.io.ByteArrayOutputStream

fun connect(
    host: String,
    port: String,
    username: String,
    password: String,
): FTPClient {
    var client: FTPClient = FTPClient()
    client.connect(host, port.toInt())
    client.login(username, password)
    client.changeWorkingDirectory("ivo/imgs/")

    return client
}

fun getFileNames(
    host: String,
    port: String,
    username: String,
    password: String
): Array<String> {
    var client: FTPClient = connect(host, port, username, password)
    var arrayFileNames = client.listNames()

    client.logout()
    client.disconnect()

    return arrayFileNames
}

fun getFTPImage(
    host: String,
    port: String,
    username: String,
    password: String,
    imageName: String
): ByteArrayOutputStream {
    var client: FTPClient = connect(host, port, username, password)

    client.setFileType(FTP.BINARY_FILE_TYPE)
    client.setFileTransferMode(FTP.BINARY_FILE_TYPE)
    client.enterLocalPassiveMode()
    client.sendCommand("OPTS UFT8 ON")

//        var fileImageLocal: File = File("/Download/ftpimage.jpg")
//        var fileOutputStream: FileOutputStream = FileOutputStream(fileImageLocal)
    var byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
    client.retrieveFile(imageName, byteArrayOutputStream)

//        fileOutputStream.flush()
//        fileOutputStream.close()

    client.logout()
    client.disconnect()
//        var bitmapImage: Bitmap = BitmapFactory.decodeFile(fileImageLocal.path)
    return byteArrayOutputStream
}