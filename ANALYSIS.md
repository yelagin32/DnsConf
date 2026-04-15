# Анализ списка GeoHide DNS

**Источник:** https://raw.githubusercontent.com/Internet-Helper/GeoHideDNS/refs/heads/main/hosts/hosts  
**Дата анализа:** 15 апреля 2026  
**Последнее обновление списка:** 15 апреля 2026

---

## 📊 Общая статистика

| Параметр | Значение |
|----------|----------|
| Всего строк в файле | 3,610 |
| Активных записей (IP + домен) | 3,468 |
| Комментариев | 80 |
| Пустых строк | 61 |

---

## 🔀 Редиректы (проксирование через GeoHide)

**Назначение:** Обход блокировок для доступа к заблокированным в России сервисам

| Параметр | Значение |
|----------|----------|
| Всего редиректов | 1,866 |
| Уникальных доменов | 1,037 |

### Прокси-серверы GeoHide:
- `45.155.204.190`
- `37.230.192.51`
- `31.25.239.132`

### Популярные проксируемые сервисы:

#### AI и ML сервисы:
- **ChatGPT / OpenAI** - chatgpt.com, api.openai.com, android.chat.openai.com
- **Claude (Anthropic)** - claude.ai, api.claude.ai, anthropic.com, console.anthropic.com
- **Google AI** - ai.google.dev, aistudio.google.com, generativelanguage.googleapis.com
- **Grok (X.AI)** - grok.x.ai, accounts.x.ai
- **ElevenLabs** - elevenlabs.io, api.elevenlabs.io
- **DeepL** - deepl.com, api.deepl.com, api-free.deepl.com

#### Разработка и инструменты:
- **JetBrains** - jetbrains.com, account.jetbrains.com, plugins.jetbrains.com
- **GitHub** (частично)
- **Linear.app** - linear.app, api.linear.app
- **Notion** - notion.so, api.notion.com
- **Framer** - framer.com, api.framer.com

#### Социальные сети и коммуникации:
- **Instagram** - instagram.com, www.instagram.com, b.i.instagram.com
- **Facebook** - facebook.com, www.facebook.com, fbcdn.net
- **Twitch** - twitch.tv, www.twitch.tv
- **Discord** (голосовые серверы)
- **Truth Social**
- **Guilded**

#### Стриминг и медиа:
- **Spotify** - spotify.com, accounts.spotify.com, api-partner.spotify.com
- **Deezer** - deezer.com, api.deezer.com, account.deezer.com

#### Другие популярные сервисы:
- **AMD** - amd.com, www.amd.com
- **Nvidia** - nvidia.com
- **Intel** - intel.com
- **Autodesk** - autodesk.com, accounts.autodesk.com
- **Broadcom** - broadcom.com, support.broadcom.com
- **Canva** - canva.com, www.canva.com
- **Patreon** - patreon.com
- **Badoo** - badoo.com, badoo.app
- **Imgur** - imgur.com, api.imgur.com
- **Weather.com**
- **Web Archive** (archive.org)

---

## 🚫 Блокировки (реклама и трекеры)

**Назначение:** Блокировка рекламы, трекинга и телеметрии

| Параметр | Значение |
|----------|----------|
| Всего блокировок | 1,602 |
| Уникальных доменов | 1,600 |

### Что блокируется:

#### Xiaomi / Mi (телеметрия и реклама):
- `ad.xiaomi.com`, `ad.mi.com`
- `tracking.miui.com`
- `data.mistat.xiaomi.com`
- `api.developer.xiaomi.com`
- Десятки других трекеров Xiaomi

#### Microsoft (телеметрия и реклама):
- `ads.microsoft.com`
- `adsdk.microsoft.com`
- `advertising.microsoft.com`
- `telemetry.microsoft.com`
- `watson.telemetry.microsoft.com`

#### Apple (реклама и трекинг):
- `advertising.apple.com`
- `api-adservices.apple.com`
- `iadsdk.apple.com`
- `metrics.apple.com`

#### Google (реклама):
- Различные рекламные домены Google

#### Yandex (метрика и реклама):
- Трекеры Яндекс.Метрики
- Рекламные домены

#### VK.com (трекинг):
- Трекеры и аналитика VK

---

## 🎯 Как это работает в твоем проекте

### Для Cloudflare:
1. **Редиректы** → создаются Gateway Lists с IP-адресами прокси
2. **Блокировки** → создаются Gateway Lists с доменами для блокировки
3. **Правила** → применяются через Gateway Rules

### Для NextDNS:
1. **Редиректы** → создаются Rewrites (домен → IP прокси)
2. **Блокировки** → добавляются в Denylist

---

## 💡 Выводы

Этот список решает две задачи:

1. **Обход блокировок** (1,037 доменов) - доступ к заблокированным в России сервисам через прокси GeoHide
2. **Блокировка рекламы** (1,600 доменов) - защита от трекинга и рекламы (Xiaomi, Microsoft, Apple, Google, Yandex)

Список активно поддерживается и обновляется ежедневно.

---

## 🔗 Источники данных списка

Согласно заголовку файла, домены взяты из:
- https://dns.geohide.ru:8443
- https://info.dns.malw.link/hosts
- https://iplist.opencck.org/ru
- https://freedom.mafioznik.xyz/file/hosts

**Примечание:** В итоговом hosts указано меньше сайтов, чем проксируется через DNS сервер GeoHide.
