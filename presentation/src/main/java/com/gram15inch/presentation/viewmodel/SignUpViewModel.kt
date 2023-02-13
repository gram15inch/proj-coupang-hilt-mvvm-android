package com.gram15inch.presentation.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gram15inch.presentation.base.ErrorHandleViewModel
import com.gram15inch.domain.model.terms.SignUpTerms
import com.clone.mycoupang.domain.policy.SignUpState
import com.gram15inch.domain.model.user.SignUpRequest
import com.gram15inch.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    ErrorHandleViewModel() {
    var email = MutableStateFlow("")
    var password = MutableStateFlow("")
    var userName = MutableStateFlow("")
    var phone = MutableStateFlow("")
    var terms = MutableStateFlow(0)

    private val _signUpState = MutableStateFlow(SignUpState.NONE)
    val signUpState get() = _signUpState.asLiveData()

    fun signUp() {
       // testUserInput()
        viewModelScope.launch {
            val request= SignUpRequest(
                terms.value,
                email.value,
                password.value,
                phone.value,
                userName.value
            )
            userRepository.postSignUp(request)
                .apply {
                    if (this != null) {
                        _signUpState.emit(SignUpState.SUCCESS)
                    } else
                        _signUpState.emit(SignUpState.FAIL)
                }
        }
    }

    companion object {
        val TERMS_AGE = SignUpTerms("age", "[필수] 만 14세 이상입니다.")
        val TERMS_CP = SignUpTerms(
            "cp", "[필수] 쿠팡 이용약관 동의",
            "[ 쿠팡 이용 약관 ]\n" +
                    "\n" +
                    "제1장 총칙\n" +
                    "\n" +
                    "제 1 조 (목적)\n" +
                    "이 약관은 쿠팡 주식회사(이하 “회사”)가 운영하는 사이버몰에서 제공하는 서비스와 이를 이용하는 회원의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.\n" +
                    "\n" +
                    "제 2 조 (용어의 정의)\n" +
                    "이 약관에서 사용하는 용어의 정의는 다음과 같습니다. 그리고 여기에서 정의되지 않은 이 약관상의 용어의 의미는 일반적인 거래관행에 따릅니다.\n" +
                    "1. “사이버몰”이란 회사가 상품 또는 용역 등(일정한 시설을 이용하거나 용역을 제공받을 수 있는 권리를 포함하며, 이하 “상품 등”)을 회원에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 상품 등을 거래할 수 있도록 설정한 가상의 영업장(http://www.coupang.com 등 회사가 운영하는 웹사이트 및 모바일 웹, 앱 등을 모두 포함)을 의미합니다."
        )

        val TERMS_EF = SignUpTerms(
            "ef", "[필수] 전자금융거래 이용약관 동의",
            "쿠팡페이㈜ 전자금융거래이용약관\n" +
                    "\n" +
                    "제 1장. 총 칙\n" +
                    "제1조(목적)\n" +
                    "본 약관은 쿠팡페이 주식회사(이하 \"회사\"라 합니다)가 제공하는 전자지급결제대행서비스 및 결제대금예치서비스, 선불전자지급수단 발행 및 관리 서비스(이하 통칭하여 “전자금융거래서비스”라 합니다)를 이용자가 이용함에 있어 회사와 이용자 사이의 전자금융거래에 관한 기본적인 사항을 정함을 목적으로 합니다.\n" +
                    "\n" +
                    "제2조 (용어의 정의)\n" +
                    "본 약관에서 정하는 용어의 정의는 다음과 같으며, 본 조 및 본 약관에서 별도로 정의하지 아니한 용어의 정의는 「전자금융거래법」 등 관련법령이 정한 바에 의합니다.\n" +
                    "1. \"전자금융거래\"라 함은 회사가 전자적 장치를 통하여 전자금융거래서비스를 제공하고, 이용자가 회사의 종사자와 직접 대면하거나 의사소통을 하지 아니하고 자동화된 방식으로 이를 이용하는 거래를 말합니다."
        )
        val TERMS_PR = SignUpTerms(
            "pr", "[필수] 개인정보 수집 및 이용 동의",
            "[필수] 개인정보 수집 및 이용 동의약관\n" +
                    "\n" +
                    "아이디(이메일), 이름, 휴대폰번호, 비밀번호\n" +
                    "회원 가입 및 이용자 식별, 회원관리\n" +
                    "회원탈퇴 시 90일간 보관 후 파기\n" +
                    "\n" +
                    "아이디(이메일), 이름, 휴대폰번호, 서비스 이용기록, 연령 및 성별\n" +
                    "개인화 서비스 제공\n" +
                    "회원탈퇴 시 90일간 보관 후 파기\n" +
                    "\n" +
                    "부정행위 탐지된 아이디(이메일), 이름, 휴대폰번호, 부정행위 기록\n" +
                    "부정행위 방지\n" +
                    "회원탈퇴 시 180일간 보관 후 파기\n" +
                    "\n" +
                    "연령 및 성별 정보는 회원 가입 이후 본인 확인을 진행한 이용자에 한해 활용됩니다.\n" +
                    "\n" +
                    "동의를 거부할 수 있으나 동의 거부 시 서비스 이용이 제한됩니다."
        )
        val TERMS_PRT = SignUpTerms(
            "prt", "[필수] 개인정보 제3자 제공동의",
            "[필수] 개인정보 제 3자 제공 동의에 대한 약관\n" +
                    "\n" +
                    "아이디(이메일), 이름, 휴대폰번호, 비밀번호\n" +
                    "회원 가입 및 이용자 식별, 회원관리\n" +
                    "회원탈퇴 시 90일간 보관 후 파기\n" +
                    "\n" +
                    "아이디(이메일), 이름, 휴대폰번호, 서비스 이용기록, 연령 및 성별\n" +
                    "개인화 서비스 제공\n" +
                    "회원탈퇴 시 90일간 보관 후 파기\n" +
                    "\n" +
                    "부정행위 탐지된 아이디(이메일), 이름, 휴대폰번호, 부정행위 기록\n" +
                    "부정행위 방지\n" +
                    "회원탈퇴 시 180일간 보관 후 파기\n" +
                    "\n" +
                    "연령 및 성별 정보는 회원 가입 이후 본인 확인을 진행한 이용자에 한해 활용됩니다.\n" +
                    "\n" +
                    "동의를 거부할 수 있으나 동의 거부 시 서비스 이용이 제한됩니다.\n"
        )
        val TERMS_MT = SignUpTerms(
            "mt", "[선택] 마케팅 목적의 개인정보 수집 및 이용",
            "[선택] 마케팅 목적의 개인정보 수집 및 이용 동의\n" +
                    "\n" +
                    "서비스 이용기록, 연령 및 성별\n" +
                    "인구통계학적 특성과 이용자의 관심 및 성향의 추정을 통한 맞춤형 광고에 활용\n" +
                    "동의 철회 시 맞춤형 광고 차단, 회원 탈퇴 시 파기\n" +
                    "\n" +
                    "아이디(이메일), 휴대폰번호\n" +
                    "동의 철회 시 맞춤형 광고 차단, 회원 탈퇴 시 파기\n" +
                    "회원 탈퇴 시 파기\n" +
                    "\n" +
                    "연령 및 성별 정보는 회원 가입 이후 본인 확인을 진행한 이용자에 한해 활용됩니다.\n" +
                    "\n" +
                    "동의를 거부할 수 있으며, 동의를 거부하셔도 서비스를 이용하실 수 있습니다."
        )
        val TERMS_AD_TITLE = SignUpTerms(
            "adTitle", "[선택] 광고성 정보 수신 동의",
            "[선택] 마케팅 목적의 개인정보 수집 및 이용 동의\n" +
                    "\n" +
                    "서비스 이용기록, 연령 및 성별\n" +
                    "인구통계학적 특성과 이용자의 관심 및 성향의 추정을 통한 맞춤형 광고에 활용\n" +
                    "동의 철회 시 맞춤형 광고 차단, 회원 탈퇴 시 파기\n" +
                    "\n" +
                    "아이디(이메일), 휴대폰번호\n" +
                    "동의 철회 시 맞춤형 광고 차단, 회원 탈퇴 시 파기\n" +
                    "회원 탈퇴 시 파기\n" +
                    "\n" +
                    "연령 및 성별 정보는 회원 가입 이후 본인 확인을 진행한 이용자에 한해 활용됩니다.\n" +
                    "\n" +
                    "동의를 거부할 수 있으며, 동의를 거부하셔도 서비스를 이용하실 수 있습니다."
        )
        val TERMS_EM = SignUpTerms("em", "[선택] 이메일 수신동의")
        val TERMS_SMS = SignUpTerms("sms", "[선택] SMS, SNS 수신 동의")
        val TERMS_WEB = SignUpTerms("web", "[선택] 앱 푸시 수신 동의")
    }
}