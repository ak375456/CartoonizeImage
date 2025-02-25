package com.example.cartoonizeimage.util

enum class CartoonStyle(val value: String) {
    PIXAR("pixar"),
    PIXAR_PLUS("pixar_plus"),
    THREE_D_CARTOON("3d_cartoon"),
    ANGEL("angel"),
    ANGEL_PLUS("angel_plus"),
    DEMON("demon"),
    UKIYOE_CARTOON("ukiyoe_cartoon"),
    AM_CARTOON("amcartoon"),
    WESTERN("western"),
    AVATAR("AVATAR"),
    JP_CARTOON("jpcartoon"),
    JP_CARTOON_HEAD("jpcartoon_head"),
    HK_CARTOON("hkcartoon"),
    CLASSIC_CARTOON("classoc_cartoon"),
    TC_CARTOON("tccartoon"),
    ANIME("anime"),
    THREE_D("3d"),
    HAND_DRAWN("handdrawn"),
    SKETCH("sketch"),
    ART_STYLE("artstyle"),
    COMIC("comic"),
    ANIMATION_3D("animation3d"),
    FULL("full"),
    GAME_3D("3d_game");

    companion object {
        fun fromValue(value: String): CartoonStyle? {
            return CartoonStyle.entries.find { it.value == value }
        }
    }
}