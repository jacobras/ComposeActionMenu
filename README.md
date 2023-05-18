# Compose Action Menu

This library provides an easy-to-use action menu for Compose, since Compose doesn't offer this by default.

[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-ComposeActionMenu-green.svg?style=flat )]( https://android-arsenal.com/details/1/8261 )

[![Android Weekly](https://androidweekly.net/issues/issue-499/badge)](https://androidweekly.net/issues/issue-499/)

## Features

- Icons (optional);
- Selectable/checkable items;
- Nested sub menus;
- Automatic overflow for items that don't fit the specified maximum.

![Animated preview image](preview.gif)

# Installation

```groovy
repositories {
    google()
    maven { url "https://jitpack.io" }
}
dependencies {
    implementation "com.github.jacobras:ComposeActionMenu:1.2.0"
}
```

## Compose version

This library uses the Compose BOM (Bill of Materials). Each version depends on specific Compose dependencies.

See the Android docs for information about the specific Compose library versions in each BOM: https://developer.android.com/jetpack/compose/bom/bom-mapping.

<table>
 <tr>
  <td>Compose 1.1.0-rc01</td><td><img alt="JitPack" src="https://img.shields.io/badge/jitpack-v1.0.0-blue"></td>
 </tr>
 <tr>
  <td>Compose 2023.01.00</td><td><img alt="JitPack" src="https://img.shields.io/badge/jitpack-v1.1.0-blue"></td>
 </tr>
 <tr>
  <td>Compose 2023.05.01</td><td><img alt="JitPack" src="https://img.shields.io/badge/jitpack-v1.2.0-blue"></td>
 </tr>
</table>

# Usage

```kotlin
val toolbarActions = listOf(
    RegularActionItem(
        key = "settings",
        titleResId = R.string.settings,
        onClick = { /* TODO: Open settings screen */ }
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
    title = R.string.group_title,
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

# Production example

My note taking app uses ComposeActionMenu:

<https://play.google.com/store/apps/details?id=nl.jacobras.notes>

![](preview_notes.png)

# License

```
MIT License

Copyright (c) 2021 Jacob Ras

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
