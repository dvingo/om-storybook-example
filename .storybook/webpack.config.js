const path = require('path')

module.exports = {
  resolve: {
    root: [
      path.join(__dirname, '..', 'src'),
      path.join(__dirname, '..', 'resources', 'public', 'js', 'compiled')
    ]
  }
}
