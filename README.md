# MLBBPlayerInfoFetcher
MLBB Player Info Fetcher Library for Android

## Screenshots
<p align="left">
<img src="/images/Screenshot_2023-11-01-11-00-26-37_64491be2e18bb8e40d5d1fd1ea49c648.jpg" width=25%/>
<img src="/images/Screenshot_2023-11-01-11-00-47-40_64491be2e18bb8e40d5d1fd1ea49c648.jpg" width=25%/>
</p>

## Gradle
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
        implementation 'com.github.sudoxE7:MLBBPlayerInfoFetcher:master-SNAPSHOT'
}
```

## Default Usage
Add in your Activity file.
```java
int playerId = 1224417029;
int serverId = 11439;

new MLBBPlayerInfoFetcher(this, playerId, serverId)
        .fetchPlayerInfo(new MLBBPlayerInfoFetcherListener() {
            @Override
            public void onPlayerInfoFetched(String imageUrl, String playerName) {
                ((TextView) findViewById(R.id.textview1)).setText(playerName);
            }
        });
```

#
// Credit to Exodus

