package com.example.atlmovaapp.screen.profile.privacy_policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.atlmovaapp.adapter.PrivacyPolicyAdapter
import com.example.atlmovaapp.databinding.FragmentPrivacyPolicyBinding
import com.example.atlmovaapp.model.privacy_policy.PrivacyPolicyModel

class PrivacyPolicyFragment : Fragment() {
    private lateinit var binding: FragmentPrivacyPolicyBinding
    private val adapter = PrivacyPolicyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrivacyPolicyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvPrivacyPolicy.adapter = adapter

        val privacyPolicyList = arrayListOf<PrivacyPolicyModel>()
        privacyPolicyList.add(
            PrivacyPolicyModel(
                "1. Types of Data We Collect",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            )
        )
        privacyPolicyList.add(
            PrivacyPolicyModel(
                "2. Use of Your Personal Data",
                "Magna etiam tempor orci eu lobortis elementum nibh. Vulputate enim nulla aliquet porttitor lacus. Orci sagittis eu volutpat odio. Cras semper auctor neque vitae tempus quam pellentesque nec. Non quam lacus suspendisse faucibus interdum posuere lorem ipsum dolor. Commodo elit at imperdiet dui. Nisi vitae suscipit tellus mauris a diam. Erat pellentesque adipiscing commodo elit at imperdiet dui. Mi ipsum faucibus vitae aliquet nec ullamcorper. Pellentesque pulvinar pellentesque habitant morbi tristique senectus et."
            )
        )
        privacyPolicyList.add(
            PrivacyPolicyModel(
                "3. Disclosure of Your Personal Data",
                "Consequat id porta nibh venenatis cras sed. Ipsum nunc aliquet bibendum enim facilisis gravida neque. Nibh tellus molestie nunc non blandit massa. Quam pellentesque nec nam aliquam sem et tortor consequat id. Faucibus vitae aliquet nec ullamcorper sit amet risus. Nunc consequat interdum varius sit amet. Eget magna fermentum iaculis eu non diam phasellus vestibulum. Pulvinar pellentesque habitant morbi tristique senectus et. Lorem donec massa sapien faucibus et molestie. Massa tempor nec feugiat nisl pretium fusce id. Lacinia at quis risus sed vulputate odio. Integer vitae justo eget magna fermentum iaculis. Eget gravida cum sociis natoque penatibus et magnis."
            )
        )
        privacyPolicyList.add(
            PrivacyPolicyModel(
                "4. Data Security",
                "We value your trust in providing us your Personal Data, thus we strive to use commercially acceptable means of protecting it. However, please remember that no method of transmission over the Internet, or method of electronic storage is 100% secure and reliable, and we cannot guarantee its absolute security."
            )
        )

        privacyPolicyList.add(
            PrivacyPolicyModel(
                "5. Children's Privacy",
                "Our Service does not address anyone under the age of 13. We do not knowingly collect personally identifiable information from children under 13. If you are a parent or guardian and you are aware that your child has provided us with Personal Data, please contact us so that we can take necessary actions."
            )
        )

        privacyPolicyList.add(
            PrivacyPolicyModel(
                "6. Changes to This Privacy Policy",
                "We may update our Privacy Policy from time to time. Thus, you are advised to review this page periodically for any changes. We will notify you of any changes by posting the new Privacy Policy on this page. These changes are effective immediately after they are posted."
            )
        )


        adapter.updateList(privacyPolicyList)

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}