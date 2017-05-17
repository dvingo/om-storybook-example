const webpack = require('webpack')
const path = require('path')

const config = {
  entry: path.join(__dirname, 'src', 'main.js'),
  output: {
    path: path.join(__dirname, 'resources', 'public', 'js', 'compiled'),
    filename: 'jsDeps.js'
  }
}

module.exports = config
