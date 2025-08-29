package com.rudresh05.pathai.`models`

// Yeh poore API response ko represent karta hai
data class YoutubeSearchResponse(
    val items: List<VideoItem>
)

// Yeh list ke andar har ek video item ko represent karta hai
data class VideoItem(
    val id: VideoId,
    val snippet: Snippet
)

// Yeh video ki ID hold karta hai
data class VideoId(
    val videoId: String
)

// Yeh video ki details jaise title, description, aur thumbnails hold karta hai
data class Snippet(
    val title: String,
    val description: String,
    val channelTitle: String,
    val thumbnails: Thumbnails
)

// Yeh alag-alag quality ke thumbnails hold karta hai
data class Thumbnails(
    val high: Thumbnail
)

// Yeh ek single thumbnail (image) ka URL hold karta hai
data class Thumbnail(
    val url: String
)
