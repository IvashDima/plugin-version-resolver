package com.example.repository

import com.example.model.Plugin
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryPluginRepository : PluginRepository {
    private val store = ConcurrentHashMap<String, Plugin>()

    override fun findById(id: String): Plugin? = store[id]

    override fun save(plugin: Plugin) {
        store[plugin.id] = plugin
    }

    override fun findAll(): List<Plugin> = store.values.toList()
}
