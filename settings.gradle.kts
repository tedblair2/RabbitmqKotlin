plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "RabbitmqKotlin"
include("first:sender")
findProject(":first:sender")?.name = "sender"
include("first:receiver")
findProject(":first:receiver")?.name = "receiver"
include("workQueue:sender")
findProject(":workQueue:sender")?.name = "sender"
include("workQueue:receiver")
findProject(":workQueue:receiver")?.name = "receiver"
include("pubsub:sender")
findProject(":pubsub:sender")?.name = "sender"
include("pubsub:receiver")
findProject(":pubsub:receiver")?.name = "receiver"
include("routing:sender")
findProject(":routing:sender")?.name = "sender"
include("routing:receiver")
findProject(":routing:receiver")?.name = "receiver"
include("topic:sender")
findProject(":topic:sender")?.name = "sender"
include("topic:receiver")
findProject(":topic:receiver")?.name = "receiver"
