import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import {faBars, faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import {faBell, faEnvelope, faSquarePlus, faUser} from "@fortawesome/free-regular-svg-icons";

import homeIcon from "../../img/home/icon_home.svg";


const HomePage = () => {

    return (
        <div className="bg-white flex flex-row justify-center">
            <div className="bg-white relative flex-auto">
                <div className="absolute top-[66px] left-[62px] text-black text-[24px] font-sans font-normal tracking-[0] leading-[normal]">
                    로고 이미지
                </div>
                <div className="top-[162px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <img
                        className="relative w-[31.2px] h-[28.09px] mt-[-1.60px] mb-[-1.60px] ml-[-1.60px]"
                        alt="Icon home"
                        src={homeIcon}
                    />
                    <div className="relative w-fit mt-[-0.56px] text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        홈
                    </div>
                </div>
                <div className="top-[376px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <FontAwesomeIcon className="relative w-[28px] h-[28px]" icon={faBell} />
                    <div className="relative w-fit text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        알림
                    </div>
                </div>
                <div className="top-[448px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <FontAwesomeIcon className="relative w-[28px] h-[28px]" icon={faUser} />
                    <div className="relative w-fit text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        프로필
                    </div>
                </div>
                <div className="top-[520px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <FontAwesomeIcon className="relative w-[27px] h-[30px]" icon={faSquarePlus} />
                    <div className="relative w-fit text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        게시하기
                    </div>
                </div>
                <div className="top-[306px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <FontAwesomeIcon className="relative w-[28px] h-[28px]" icon={faEnvelope} />
                    <div className="relative w-fit text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        메시지
                    </div>
                </div>
                <div className="top-[234px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <FontAwesomeIcon className="relative w-[28px] h-[28px]" icon={faMagnifyingGlass} />
                    <div className="relative w-fit text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        검색
                    </div>
                </div>
                <div className="top-[809px] inline-flex items-center gap-[24px] absolute left-[62px]">
                    <FontAwesomeIcon className="relative w-[28px] h-[28px]" icon={faBars} />
                    <div className="relative w-fit text-[#3e3e3e] text-[20px] whitespace-nowrap font-sans font-normal tracking-[0] leading-[normal]">
                        더보기
                    </div>
                </div>
            </div>
            <div className="flex-auto relative top-[66px]">
                게시글 표시 구역
            </div>
        </div>
    );
};

export default HomePage