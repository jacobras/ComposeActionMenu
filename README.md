# Compose Action Menu

![Android](https://img.shields.io/badge/-android-6EDB8D.svg?style=flat)
![iOS](http://img.shields.io/badge/-ios-CDCDCD.svg?style=flat)
![JVM](https://img.shields.io/badge/-jvm-DB413D.svg?style=flat)

This multi-platform library provides an easy-to-use action menu for Compose, since Compose doesn't offer this by default.

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-ComposeActionMenu-green.svg?style=flat )]( https://android-arsenal.com/details/1/8261 )

[![Android Weekly](https://androidweekly.net/issues/issue-499/badge)](https://androidweekly.net/issues/issue-499/)

## Features

- Icons (optional);
- Selectable/checkable items;
- Nested sub menus;
- Automatic overflow for items that don't fit the specified maximum;
- Kotlin Multiplatform (KMP) since version 2.0.0, supporting Android, iOS and JVM (desktop).

![Animated preview image](preview.gif)

# Installation

```kotlin
dependencies {
    implementation("nl.jacobras:compose-action-menu:2.0.0")
}
```

Note: version 2 and newer are available from Maven Central, whereas previous versions were distributed through JitPack.

See also: [Migrating to V2](#migrating-from-v1-to-v2).

## Compose version

Each version depends on specific Compose dependencies.

<table>
 <tr>
  <td><img alt="JitPack" src="https://img.shields.io/badge/jitpack-v1.0.0-blue"></td><td>Compose 1.1.0-rc01</td>
 </tr>
 <tr>
  <td><img alt="JitPack" src="https://img.shields.io/badge/jitpack-v1.1.0-blue"></td><td>Compose 1.3.3</td>
 </tr>
 <tr>
  <td><img alt="JitPack" src="https://img.shields.io/badge/jitpack-v1.2.0-blue"></td><td>Compose 1.4.3</td>
 </tr>
 <tr>
  <td><img alt="JitPack" src="https://img.shields.io/badge/mavencentral-v2.0.0-blue"></td><td>Compose Multiplatform 1.5.1</td>
 </tr>
</table>

# Usage

```kotlin
val toolbarActions = listOf(
    RegularActionItem(
        key = "search",
        title = stringResource(R.string.search),
        iconVector = Icons.Filled.Search,
        onClick = { /* TODO: Open search screen */ }
    )
)

TopAppBar(
    actions = { ActionMenu(items = toolbarActions) }
)
```

## Supported item types

Several different type of actions can be used. These are all sub classes of `ActionItem`.

- Regular item: `RegularActionItem`;
- Checkable item: `CheckableActionItem`;
- Radio-toggleable item: `RadioActionItem`;
- Groups: `GroupActionItem`.

### Creating a group

Is as simple as:

```kotlin
val subOption1 = RegularActionItem(key = "subOption1", /* ... */)
val subOption2 = RegularActionItem(key = "subOption2", /* ... */)
val subOption3 = RegularActionItem(key = "subOption3", /* ... */)

val group = GroupActionItem(
    key = "myGroup",
    title = stringResource(R.string.group_title),
    childOptions = listOf(subOption1, subOption2, subOption3)
)
```

## Customisation

`ActionMenu` takes some parameters:

- `maxNumberOfIcons` Number of icons to show, including overflow menu icon (if needed);
- `colors` Optional color configuration.

`ActionItem`s can be customised too:

- Use `iconVector` or `iconDrawable` to show an icon next to the item's title;
- Set `showAsAction` to change if an item will be shown as an icon or in the overflow menu.

# Testing

Every menu item has a test tag attached to it which is a combination of a `"ActionMenu"` prefix and the item's `key`. Example test usage:

```kotlin
// Menu configuration:
val item = RegularActionItem(key = "myKey", /* ... */)

// Test:
composeTestRule.onNodeWithTag("ActionMenu#myKey").performClick()
```

There's a reserved key for the overflow icon: `"ActionMenu#overflow"`.

# Migrating from v1 to v2

Compose Action Menu version 2 is built using KMP. Android-specific resource support is replaced with broader string + Painter support.

1.x:

```kotlin
RegularActionItem(
    titleResId = R.string.search,
    iconDrawable = R.drawable.search
)
```

2.x:

```kotlin
RegularActionItem(
    title = stringResource(R.string.search),
    icon = painterResource(R.drawable.search)
)
```

# Sample apps

The repository contains two sample apps.

* Run Android: `gradlew sample-app:installDebug`
* Run Desktop: `gradlew sample-desktop:run`

# Production example

My note taking app uses ComposeActionMenu:

<https://play.google.com/store/apps/details?id=nl.jacobras.notes>

![](preview_notes.png)