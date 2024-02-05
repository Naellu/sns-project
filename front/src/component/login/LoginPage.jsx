import { useState, useCallback } from 'react'
import { Input } from "@material-tailwind/react";

import socialLoginVector01 from '../../img/login/Vector 12_10.png'
import socialLoginVector02 from '../../img/login/Vector 22_11.png'
import kakaoLoginImg from '../../img/login/kakao_login_large 14_30.png'
import googleLoginImg from '../../img/login/android_light_sq_na@4x 14_29.png'
// import mainLogo from './main_logo_img_file.png'

const LoginPage = () => {
    const clickLogEvent = () => {
        console.log("element 클릭됨");
    }

    return (
        <div className="bg-white flex flex-row justify-center w-full">
            <div className="bg-white w-[1440px] h-[1024px]">
                <div className="relative w-[369px] h-[644px] top-[171px] left-[534px]">

                    <div className="absolute w-[369px] h-[544px] top-0 left-0 border-[0.5px] border-solid border-[#a5a2a2]">
                        <div
                            className="absolute w-[249px] h-[46px] top-[50px] left-[60px] [font-family:'Inter-Regular',Helvetica] font-normal text-black text-[40px] text-center tracking-[0] leading-[normal] ">NAELLU
                        </div>
                        {/*<img*/}
                        {/*    className="absolute w-[249px] h-[86px] top-[40px] left-[60px] object-cover"*/}
                        {/*    // src={mainLogo}*/}
                        {/*/>*/}

                        <input
                            className="absolute w-[249px] h-[44px] top-[153px] left-[60px] bg-[#F5F5F5] border-[0.1px] border-solid border-[#C9C9C9] [font-family:'Inter-Regular',Helvetica] font-normal text-[#8d8d8d] text-[12px] tracking-[0] leading-[normal] p-3"
                            placeholder={"아이디 또는 이메일"}/>
                        <input
                            className="absolute w-[249px] h-[44px] top-[227px] left-[60px] bg-[#F5F5F5] border-[0.1px] border-solid border-[#C9C9C9] [font-family:'Inter-Regular',Helvetica] font-normal text-[#8d8d8d] text-[12px] tracking-[0] leading-[normal] p-3"
                            placeholder={"비밀번호"}/>

                        <div
                            className="absolute w-[249px] h-[39px] top-[304px] left-[60px] bg-[#abeaff] rounded-[10px]">
                            <button
                                className="top-[10px] left-[105px] [font-family:'Inter-Bold',Helvetica] font-bold text-white text-[16px] whitespace-nowrap absolute tracking-[0] leading-[normal]"
                                onClick={clickLogEvent}>
                                로그인
                            </button>
                        </div>

                        <div className="absolute w-[247px] h-[19px] top-[375px] left-[62px]">
                            <img className="absolute w-[92px] h-px top-[10px] left-0" alt="Left vector"
                                 src={socialLoginVector01}/>
                            <img className="absolute w-[92px] h-px top-[11px] left-[154px]" alt="Vector"
                                 src={socialLoginVector02}/>
                            <div
                                className="absolute -top-px left-[116px] [font-family:'Inter-Light',Helvetica] font-light text-black text-[16px] tracking-[0] leading-[normal] whitespace-nowrap">
                                or
                            </div>
                        </div>

                        <img
                            className="absolute w-[94px] h-[46px] top-[414px] left-[70px] object-cover"
                            alt="Kakao login large"
                            src={kakaoLoginImg}
                            onClick={clickLogEvent}
                        />
                        <img
                            className="absolute w-[46px] h-[46px] top-[414px] left-[240px] object-cover"
                            alt="Android light sq na"
                            src={googleLoginImg}
                            onClick={clickLogEvent}
                        />

                        <div
                            className="h-[16px] top-[498px] left-[119px] [font-family:'Inter-Light',Helvetica] font-light text-[#acacac] text-[13px] absolute tracking-[0] leading-[normal]">
                            비밀번호를 잊으셨나요?
                        </div>
                    </div>

                    <div
                        className="absolute w-[369px] h-[80px] top-[564px] left-0 border-[0.5px] border-solid border-[#a4a2a2]">
                        <div className="relative w-[215px] h-[19px] top-[32px] left-[80px]">
                            <div
                                className="h-[19px] -top-px left-0 [font-family:'Inter-Light',Helvetica] font-light text-black text-[16px] text-center whitespace-nowrap absolute tracking-[0] leading-[normal]">
                                계정이 없으신가요?
                            </div>
                            <div
                                className="h-[19px] -top-px left-[141px] [font-family:'Inter-Bold',Helvetica] font-bold text-[#73aaea] text-[16px] text-center whitespace-nowrap absolute tracking-[0] leading-[normal]">
                                가입하기
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default LoginPage
