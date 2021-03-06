package com.vivcom.videoshop.presentation.deep_link

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.vivcom.videoshop.R
import com.vivcom.videoshop.repository.tool.Constants
import kotlinx.android.synthetic.main.deeplink_fragment.*

class DeepLinkFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.deeplink_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs = DeepLinkFragmentArgs.fromBundle(arguments!!)
        tv_title_deep_link.text = safeArgs.args

        val notificationButton = view.findViewById<Button>(R.id.send_notification_button)
        notificationButton.setOnClickListener {
            val editArgs = args_edit_text.text
            val args = Bundle()
            args.putString(Constants.Keys.ID_MOVIE, editArgs.toString() + ".0")

            val deeplink = findNavController().createDeepLink()
                .setDestination(R.id.video_detail_fragment)
                .setArguments(args)
                .createPendingIntent()

            val notificationManager =
                context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH
                    )
                )
            }

            val builder = NotificationCompat.Builder(
                context!!, "deeplink"
            )
                .setContentTitle("Navigation")
                .setContentText("Deep link desde Android")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(deeplink)
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }
}
