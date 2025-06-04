package br.com.fiap.health.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailSender {

    suspend fun sendEmail(subject: String, body: String) = withContext(Dispatchers.IO) {
        try {
            val props = Properties().apply {
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.host", "smtp.gmail.com")
                put("mail.smtp.port", "587")
            }

            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(
                        "seuemail@gmail.com", // 🔐 Email de envio
                        "sua_senha_de_app"    // 🔐 Senha de app (não a senha normal)
                    )
                }
            })

            val message = MimeMessage(session).apply {
                setFrom(InternetAddress("seuemail@gmail.com"))
                setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("psicologa@empresa.com")
                )
                setSubject(subject)
                setText(body)
            }

            Transport.send(message)
            Log.d("EmailSender", "Email enviado com sucesso!")

        } catch (e: Exception) {
            Log.e("EmailSender", "Erro ao enviar email: ${e.message}")
        }
    }
}
